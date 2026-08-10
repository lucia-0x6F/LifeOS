#!/usr/bin/env bash
set -euo pipefail

project_dir="$(cd "$(dirname "$0")" && pwd)"
classes_dir="$project_dir/.lifeos-classes"

rm -rf "$classes_dir"
mkdir -p "$classes_dir"

javac -cp "$project_dir/lib/*" \
    -d "$classes_dir" \
    $(find "$project_dir/src/main" -name '*.java' -print)

cd "$project_dir"
exec java -cp "$classes_dir:$project_dir/lib/*" ui.gui.Main
