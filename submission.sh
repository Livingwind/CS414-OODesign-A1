mkdir -p a1 &&
  mv src/main/java/a1/*.java src/test/java/a1/*.java ./a1 &&
  jar cvf a1.jar ./a1
rm -r ./a1