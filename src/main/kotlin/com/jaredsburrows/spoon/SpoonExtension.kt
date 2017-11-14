package com.jaredsburrows.spoon

import java.io.File

/**
 * Variables based on the following documentation:
 * - https://developer.android.com/reference/android/support/test/runner/AndroidJUnitRunner.html
 * - https://developer.android.com/training/testing/espresso/setup.html
 * - https://github.com/square/spoon/blob/master/spoon-runner/src/main/java/com/squareup/spoon/SpoonRunner.java
 * - https://github.com/square/spoon#execution
 *
 * @author <a href="mailto:jaredsburrows@gmail.com">Jared Burrows</a>
 */
open class SpoonExtension { // Extensions cannot be final
    companion object {
        private const val DEFAULT_OUTPUT_DIRECTORY = "spoon-output"
        private const val DEFAULT_ADB_TIMEOUT_SEC = 10 * 60  // 10 minutes
    }

    ////////////////////////////////////////////////////
    // Supported directly by Spoon's SpoonRunner
    ///////////////////////////////////////////////////

    /** Path to output directory. ("$buildDir/spoon-output" by default) */
    var output: String = DEFAULT_OUTPUT_DIRECTORY
        set(value) {
            field = value
        }

    /** Whether or not debug logging is enabled. (false by default) */
    var debug: Boolean = false

    /** Whether or not animations are enabled. Disable animated gif generation. (false by default) */
    var noAnimations: Boolean = false

    /** Set ADB timeout. (minutes) (default is 10 minutes) */
    var adbTimeout: Int = DEFAULT_ADB_TIMEOUT_SEC * 1000
        set(value) {
            if (value > 0) field = value * 1000
        }

    /** Add device serials for test execution. */
    var devices = mutableSetOf<String>()

    /** Add device serials for skipping test execution. */
    var skipDevices = mutableSetOf<String>()

    /** Extra arguments to pass to instrumentation. */
    var instrumentationArgs = mutableListOf<String>()

    /** Test class name to run (fully-qualified). */
    var className: String = ""

    // TODO size

    /** Execute the tests device by device. (false by default) */
    var sequential: Boolean = false

    /** Grant all runtime permissions during installation on Marshmallow and above devices. (false by default) */
    var grantAll: Boolean = false

    /** Test method name to run (must also use className) */
    var methodName: String = ""

    /** Code coverage flag. For Spoon to calculate coverage file your app must have the `WRITE_EXTERNAL_STORAGE` permission. (false by default)
    (This option pulls the coverage file from all devices and merge them into a single file `merged-coverage.ec`.) */
    var codeCoverage: Boolean = false

    /** Fail if no device is connected. (false by default) */
    var failIfNoDeviceConnected: Boolean = false

    ////////////////////////////////////////////////////
    // Passed in via -e, extra arguments
    ///////////////////////////////////////////////////

    /** Toggle sharding. (false by default) */
    var shard: Boolean = false

    /** The number of separate shards to create. */
    var numShards: Int = 0
        set(value) {
            if (value > 0) field = value
        }

    /** The shardIndex option to specify which shard to run. */
    var shardIndex: Int = 0
        set(value) {
            if (value > 0) field = value
        }

    /** Do not fail build if a test fails, let all the tests run and finish. (false by default) */
    var ignoreFailures: Boolean = false

    ////////////////////////////////////////////////////
    // Deprecated/Renamed
    ///////////////////////////////////////////////////

    @Deprecated("Use 'output'", replaceWith = ReplaceWith("output"))
    var baseOutputDir: File = File(DEFAULT_OUTPUT_DIRECTORY)
        set(value) {
            output = value.absolutePath
            field = value
        }

    @Deprecated("Use 'grantAll'", replaceWith = ReplaceWith("grantAll"))
    var grantAllPermissions: Boolean = false
        set(value) {
            grantAll = value
            field = value
        }
}