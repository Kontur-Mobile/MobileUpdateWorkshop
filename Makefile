xcf-debug:
	./gradlew assembleSharedDebugXCFramework
	cp -R shared/build/XCFrameworks/debug/shared.xcframework iosApp/