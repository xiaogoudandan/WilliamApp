'use strict'
var Test = {};

Test.execute = function (code) {
    try {
        eval(code);
    } catch (e) {

    }
}

Test.test = function () {
    androidjs.print("成功调用");
}