#!/usr/bin/env bash
set -euo pipefail
project_dir="$(cd "$(dirname "$0")" && pwd)"
build_dir="$project_dir/.lifeos-package"
input_dir="$build_dir/input"
rm -rf "$build_dir"
mkdir -p "$input_dir/lib" "$input_dir/data" "$build_dir/classes"
find "$project_dir/src/main" -name '*.java' -print > "$build_dir/sources.txt"
javac -cp "$project_dir/lib/*" -d "$build_dir/classes" @"$build_dir/sources.txt"
cp "$project_dir/LifeOS.png" "$project_dir/image.png" "$input_dir/"
cp "$project_dir/data/LongTerm.json" "$project_dir/data/ShortTerm.json" "$input_dir/data/"
cp "$project_dir/lib/json-20251224.jar" "$input_dir/lib/"
jar --create --file "$input_dir/LifeOS.jar" --main-class ui.gui.Main -C "$build_dir/classes" .
mkdir -p "$project_dir/dist"
jpackage --type dmg --name LifeOS --input "$input_dir" --main-jar LifeOS.jar --main-class ui.gui.Main --dest "$project_dir/dist"
