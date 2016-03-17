module.exports = {
    // Documentation for GitBook is stored under "docs"
    root: './docs',
    title: 'Eventchain Documentation',
    plugins: [ "versions" ],
    pluginsConfig: {
        versions: {
            type: "branches"
        }
    },
    gitbook: '3.0.0-pre.5'
};
