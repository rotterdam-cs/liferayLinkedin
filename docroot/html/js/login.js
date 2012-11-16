(function ($) {

    var LoginPortlet = function(config) {

        this.loginType = config.loginType;

        this.defaultActionUrl = config.defaultActionUrl;
        this.logoutUrl = config.logoutUrl;
        this.forgotPasswordUrl = config.forgotPasswordUrl;
        this.resetPasswordUrl = config.resetPasswordUrl;

        this.formSelector =                 "#" + config.formSelectorId;
        this.loginSubmitSelector =          "#" + config.loginSubmitId;
        this.forgetPasswordLinkSelector =   "#" + config.forgetPasswordLinkId;
        this.loginSelector =                "#" + config.loginId;
        this.passwordSelector =             "#" + config.passwordId;
        this.login58Selector =              "#" + config.login58Id;
        this.password58Selector =           "#" + config.password58Id;

        scope = this;

        this._init(config);
    };

    $.extend(true, LoginPortlet, {

        prototype: {

            _init: function(config){
                this._bindListeners();
            },

            /*=== LISTENERS ===*/
            _bindListeners: function() {
                $(this.loginSubmitSelector).on("click", this._loginHandler);
                $(this.passwordSelector).on("keypress", this._onPasswordEnterHandler);
                $(this.forgetPasswordLinkSelector).on('click', this._forgotPasswordHandler);
            },


            /*=== HANDLERS ===*/
            _onPasswordEnterHandler: function(event){
                var enterKeyCode = 13;
                var keyCode = event.keyCode;

                if (keyCode == enterKeyCode) {
                    scope._loginEventHandler();
                }
            },

            _forgotPasswordHandler: function(){
                scope._forgotPasswordEvent();
            },

            _loginHandler: function(event){
                if (scope.loginType == "linkedIn"){
                    scope._linkedInLoginHandler(event);
                } else if (scope.loginType == "twitter"){
                    scope._twitterLoginHandler(event);
                } else {
                    scope._loginEventHandler(event);
                }
            },

            _loginEventHandler: function(event) {

                var login = $(scope.loginSelector).val();
                var password = $(scope.passwordSelector).val();

                $(scope.login58Selector).val(login);
                $(scope.password58Selector).val(password);

                var url = scope.defaultActionUrl;

                $.ajax({
                    type : "POST",
                    url : url,
                    dataType: 'json',
                    data : {
                        action : "loginAction",
                        login: login,
                        password: password
                    },
                    success : function(response) {

                        var status = response.infoMap.status;
                        var trueLogin = response.infoMap.trueLogin;

                        if (status == 'failed' || status == 'error') {

                            scope._loginErrorEvent();

                        } else if (status == 'success') {

                            if (trueLogin) {
                                $(scope.login58Selector).val(trueLogin);
                            }
                            scope._postLoginFormEvent();

                        } else if (status == 'resetPassword') {

                            scope._postLoginFormEvent();
                            scope._redirectToResetPasswordEvent();

                        } else if (status == 'mail-processing') {

                            scope._forgotPasswordEvent();
                        }
                    },
                    error: function(response) {

                    }
                });
            },

            _linkedInLoginHandler: function(event){
                var url = scope.defaultActionUrl;
                var email = $("#email").val();
                $.ajax({
                    type : "POST",
                    url : url,
                    dataType: 'json',
                    data : {
                        action : "linked_in_login",
                        email: email
                    },
                    success : function(response) {
                        var status = response.infoMap.status;
                        var userLogin = response.infoMap.userLogin;
                        var userPassword = response.infoMap.userPassword;
                        if (status == 'success') {
                            $(scope.login58Selector).val(userLogin);
                            $(scope.password58Selector).val(userPassword);
                            scope._postLoginFormEvent();
                        }
                    },
                    error: function(response) {
                    }
                });
            },

            _twitterLoginHandler: function(event){
                var url = scope.defaultActionUrl;
                var email = $("#email").val();
                $.ajax({
                    type : "POST",
                    url : url,
                    dataType: 'json',
                    data : {
                        action : "twitter_login",
                        email: email
                    },
                    success : function(response) {
                        var status = response.infoMap.status;
                        var userLogin = response.infoMap.userLogin;
                        var userPassword = response.infoMap.userPassword;
                        if (status == 'success') {
                            $(scope.login58Selector).val(userLogin);
                            $(scope.password58Selector).val(userPassword);
                            scope._postLoginFormEvent();
                        }
                    }
                });
            },

            /*=== EVENTS ===*/
            _forgotPasswordEvent: function(){
                $.ajax({
                    url : scope.logoutUrl,
                    async : false
                });
                var url = scope.forgotPasswordUrl;
                $(scope.formSelector).attr('action', url).submit();
            },

            _loginErrorEvent: function() {
                $("#passwordInputField").addClass("error");
                $("#error_div").removeClass("hidden");
                $("#msgContent").hide();
                $("#errMsgContent").show();
            },

            _postLoginFormEvent: function(){
                $(scope.formSelector).submit();
            },

            _redirectToResetPasswordEvent: function(){
                var resetPasswordUrl = scope.resetPasswordUrl;
                $.post(resetPasswordUrl);
            }
        }
    });

    window.LoginPortlet = LoginPortlet;

})(jQuery);