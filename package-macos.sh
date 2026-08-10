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
iconset_dir="$build_dir/LifeOS.iconset"
mkdir -p "$iconset_dir"
for size in 16 32 128 256 512; do
    sips -z "$size" "$size" "$project_dir/LifeOS.png" --out "$iconset_dir/icon_${size}x${size}.png" >/dev/null
    double_size=$((size * 2))
    sips -z "$double_size" "$double_size" "$project_dir/LifeOS.png" \
        --out "$iconset_dir/icon_${size}x${size}@2x.png" >/dev/null
done
iconutil -c icns "$iconset_dir" -o "$build_dir/LifeOS.icns"
jpackage --type dmg --name LifeOS --input "$input_dir" --main-jar LifeOS.jar --main-class ui.gui.Main --icon "$build_dir/LifeOS.icns" --dest "$project_dir/dist"
