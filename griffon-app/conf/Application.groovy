application {
    title = 'GriffonSample'
    startupGroups = ['griffon-sample']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "griffon-sample"
    'griffon-sample' {
        model      = 'griffon.sample.GriffonSampleModel'
        view       = 'griffon.sample.GriffonSampleView'
        controller = 'griffon.sample.GriffonSampleController'
    }

}
