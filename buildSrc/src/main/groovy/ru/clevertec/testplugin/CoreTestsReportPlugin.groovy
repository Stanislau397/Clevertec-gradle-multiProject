package ru.clevertec.testplugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.testing.Test

class CoreTestsReportPlugin implements Plugin<Project> {

    private static final String TEST_REPORTS_DIRECTORY = 'test_report'
    private static final String CORE_TESTS_PATH = 'ru.clevertec.core*';
    private static final String CORE_TESTS_REPORT_PATH = 'build/reports/tests/coreTests'
    private static final String CORE_TESTS = 'coreTests'
    private static final String TEST_REPORT = 'testReport'
    private static final String SEPARATOR = '/';

    @Override
    void apply(Project project) {

        project.tasks.register(TEST_REPORT, Copy) {
            dependsOn(CORE_TESTS)
            onlyIf {
                project.rootProject.file(TEST_REPORTS_DIRECTORY).exists()
            }
            from CORE_TESTS_REPORT_PATH
            into project.getRootDir().toString() + SEPARATOR + TEST_REPORTS_DIRECTORY
        }

        project.tasks.register(CORE_TESTS, Test).configure() {
            doFirst {
                filter {
                    includeTestsMatching(CORE_TESTS_PATH)
                }
            }
            doLast {
                if (!project.file(TEST_REPORTS_DIRECTORY).exists()) {
                    project.rootProject.file(TEST_REPORTS_DIRECTORY).mkdir()
                }
            }
        }
    }
}
