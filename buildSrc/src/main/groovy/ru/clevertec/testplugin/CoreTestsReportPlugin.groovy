package ru.clevertec.testplugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.testing.Test

class CoreTestsReportPlugin implements Plugin<Project> {

    private static final String TEST_REPORTS_DIRECTORY = 'test_report'
    private static final String CORE_TESTS_PATH = 'ru.clevertec.core*';
    private static final String UTILS_TEST_REPORTS_PATH = 'build/reports/tests/utilsTest'
    private static final String UTILS_TEST = 'utilsTest'
    private static final String TEST_REPORT = 'testReport'
    private static final String SEPARATOR = '/';

    @Override
    void apply(Project project) {

        project.tasks.register(TEST_REPORT, Copy) {
            dependsOn(UTILS_TEST)
            onlyIf {
                project.rootProject.file(TEST_REPORTS_DIRECTORY).exists()
            }
            from UTILS_TEST_REPORTS_PATH
            into project.getRootDir().toString() + SEPARATOR + TEST_REPORTS_DIRECTORY
        }

        project.tasks.register(UTILS_TEST, Test).configure() {
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
