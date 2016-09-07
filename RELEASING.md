### How to make a release

- Change the version in `build.grade` to next version
- Change version number in `README.md` to next version
- Update the CHANGELOG.md for the impending release.
- git commit -am "Make release for vX.Y.Z." (where X.Y.Z is the new version)
- ./gradlew clean bintrayUpload to publish artifact to jcenter & maven
- git checkout master 
- git rebase develop to rebase develop branch unto master
- git tag -a vX.Y.X -m "Version vX.Y.Z" (where X.Y.Z is the new version)
- git push && git push --tags
- Go to github and create a release. Add the details of the changelog to the release description
- git checkout develop
