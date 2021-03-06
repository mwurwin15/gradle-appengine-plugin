package com.google.appengine.task.appcfg

import org.gradle.api.Incubating
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory

@Incubating
class StageTask extends AppConfigTaskTemplate {
    static final String COMMAND = 'stage'
    @InputDirectory File explodedAppDirectory
    @OutputDirectory File stagedAppDirectory
    @Input @Optional boolean useRemoteResourceLimits

    @Override
    String startLogMessage() {
        'Starting to stage...'
    }

    @Override
    String errorLogMessage() {
        'An error occurred during staging.'
    }

    @Override
    String finishLogMessage() {
        'Finished staging.'
    }

    @Override
    void executeTask() {
        ant.delete(dir: getStagedAppDirectory())
        super.executeTask()
    }

    @Override
    List getParams() {
        def params = []
        if(isUseRemoteResourceLimits()) {
            params << "--use_remote_resource_limits"
        }
        params << COMMAND
        params << getExplodedAppDirectory().absolutePath
        params << getStagedAppDirectory().absolutePath
    }
}
