#!/usr/bin/env bash
set -euo pipefail
which docker > /dev/null || (echo "Please ensure docker is in your Path" && exit 1)

docker stop rabbit-docker