const messages = {
    ch: {
        message: {
            hello : '你好 国际化',
            returnHome : '返回主页',
            signOut : '退出',
            search : '搜索',
            idCard : '',
            pigId : '',
            birthday : '',

        }
    },
    en: {
        message: {
            hello: 'hello world',
            returnHome : 'return to home',
            signOut : 'Sign out',
            search : 'Search',
            idCard : '',
            pigId : '',
            birthday : '',

        }
    },
    ja: {
        message: {
            hello: 'こんにちは、世界'
        }
    }
}

// Create VueI18n instance with options
const i18n = new VueI18n({
    locale: 'en', // set locale
    messages, // set locale messages
})


// Create a Vue instance with `i18n` option

// Now the app has started!