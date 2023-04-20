#!/usr/bin/env bash

set -Eeu
#trap cleanup SIGINT SIGTERM ERR EXIT

script_dir=$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd -P)
now=$(date +%Y%m%d)

usage() {
  cat <<EOF # remove the space between << and EOF, this is due to web plugin issue
사용법: $(basename "${BASH_SOURCE[0]}") [-h or --help] [-v or --verbose]
배포시 이미지의 버전 태그는 자동으로 새 값을 지정한다.
옵션:
-h, --help      스크립트 사용방법 출력
-v, --verbose   디버그 메세지 출력 (미구현)
-t              배포 버전 태그
EOF
  exit
}

cleanup() {
  trap - SIGINT SIGTERM ERR EXIT
  # script cleanup here
}

setup_colors() {
  if [[ -t 2 ]] && [[ -z "${NO_COLOR-}" ]] && [[ "${TERM-}" != "dumb" ]]; then
    NOFORMAT='\033[0m' RED='\033[0;31m' GREEN='\033[0;32m' ORANGE='\033[0;33m' BLUE='\033[0;34m' PURPLE='\033[0;35m' CYAN='\033[0;36m' YELLOW='\033[1;33m'
  else
    NOFORMAT='' RED='' GREEN='' ORANGE='' BLUE='' PURPLE='' CYAN='' YELLOW=''
  fi
}

msg() {
  echo >&2 -e "${1-}"
  #if you want msg to file use below
  #echo >&2 -e "${1-}" >> "$script_dir/deploy_logs/$now.log"
}

die() {
  local msg=$1
  local code=${2-1} # default exit status를 1로 설정
  msg "$msg"
  exit "$code"
}

parse_params() {
  # default values of variables set from params
  bg=0

  while :; do
    case "${1-}" in
    -h | --help) usage ;;
    -v | --verbose) set -x ;;
    -?*) die "Unknown option: $1" ;;
    *) break ;;
    esac
    shift
  done
  return 0
}

parse_params "$@"
setup_colors

msg "================================================================================="
msg "DEPLOY ONZ BACK-END API SERVER"

msg "\nScript executed from :"
pwd

COUNT=$(docker images "onz-be:${now}*" | wc -l)
VERSION_NUMBER="${now}-${COUNT}"

echo ${VERSION_NUMBER}

maven_package=$(mvn clean -Dmaven.test.skip=true package)
if ! [ "${maven_package}" ]; then
    die "${RED}maven package FAIL${NOFORMAT}"
fi
msg "${GREEN}maven package success${NOFORMAT}"

docker_build=$(time docker build -t onz-be:${VERSION_NUMBER} .)
#if ! [ "${docker_build}" ]; then
#    die "Docker image build fail. ${YELLOW}check Dockerfile or Docker images${NOFORMAT}"
#fi
msg "${GREEN}Docker image build success${NOFORMAT}"

msg "docker rm onz-be old version"
docker rm --force onz-be

msg "docker run"
docker_run=$(docker run -e "SPRING_PROFILES_ACTIVE=dev" -d -p 8080:8080 --name onz-be onz-be:${VERSION_NUMBER})
if ! [ "${docker_run}" ]; then
  die "Docker Run FAILED"
fi
msg "${GREEN}Docker container run success${NOFORMAT}"

msg "ONZ BACK-END SERVER TAG : ${VERSION_NUMBER}"
msg "================================================================================="
