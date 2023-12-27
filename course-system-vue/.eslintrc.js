module.exports = {
    "env": {
        "browser": true,
        "node": true
        // "es2021": true
    },
    "extends": [
        "eslint:recommended",
        "plugin:vue/recommended"
    ],
    "overrides": [
        {
            "env": {
                "node": true
            },
            "files": [
                ".eslintrc.{js,cjs}"
            ],
            "parserOptions": {
                "sourceType": "script"
            }
        }
    ],
    "parserOptions": {
        "ecmaVersion": "12",
        "sourceType": "module"
    },
    "plugins": [
        "vue"
    ],
    "rules": {
        "no-unused-vars": [2, {
            // 允许声明未使用变量
            "vars": "local",
            // 参数不检查
            "args": "none"
            }]
    }
}
