webpackJsonp([31],{ELJb:function(t,o,e){"use strict";Object.defineProperty(o,"__esModule",{value:!0});e("rLAy");var i=e("mtWM"),a=e.n(i),n={name:"app-mine-forgot-box",data:function(){return{forgotInfo:{sendAuthCode:!0,auth_time:0,mobile:"",verifycode:"",token:""}}},methods:{getAuthCode:function(){var t=this;if(""===this.forgotInfo.mobile)return this.$vux.toast.show({type:"cancel",text:"请输入手机号"}),0;if(!/^1[3|4|5|7|8][0-9]{9}$/.test(this.forgotInfo.mobile))return this.$vux.toast.show({type:"cancel",text:"手机格式不正确"}),0;a.a.post("http://api.zhimaim.com/api/user/forget_password",{Mobile:this.forgotInfo.mobile}).then(function(o){console.log(o.data),!0===o.data.success?t.$vux.toast.show({text:"验证码已发送"}):t.$vux.toast.show({type:"cancel",text:"手机号格式不正确"})}).catch(function(t){throw t}),this.forgotInfo.sendAuthCode=!1,this.forgotInfo.auth_time=60;var o=setInterval(function(){t.forgotInfo.auth_time--,t.forgotInfo.auth_time<=0&&(t.forgotInfo.sendAuthCode=!0,clearInterval(o))},1e3)},handleForgotclick:function(t){var o=this;return""===this.forgotInfo.mobile?(this.$vux.toast.show({type:"cancel",text:"请输入手机号"}),0):/^1[3|4|5|7|8][0-9]{9}$/.test(this.forgotInfo.mobile)?""===this.forgotInfo.verifycode.trim()?(this.$vux.toast.show({type:"cancel",text:"请输入短信验证码"}),0):void a.a.post("http://api.zhimaim.com/api/user/forget_password_2",{Mobile:this.forgotInfo.mobile,VerifyCode:this.forgotInfo.verifycode}).then(function(t){!0===t.data.success&&(window.localStorage.setItem("token",t.data.data.token),o.$router.push({name:"appMineRebuild"}))}).catch(function(t){throw o.$vux.toast.show({type:"cancel",text:"校验失败"}),t}):(this.$vux.toast.show({type:"cancel",text:"手机号格式不正确"}),0)}}},s={render:function(){var t=this,o=t.$createElement,i=t._self._c||o;return i("div",{staticClass:"app-mine-forgot"},[i("div",{staticClass:"app-mine-forgot-box"},[i("div",{staticClass:"re-links"},[i("router-link",{attrs:{to:{name:"appMineLogin"}}},[i("i",{staticClass:"iconfont icon-fanhui"})])],1),t._v(" "),i("img",{attrs:{src:e("iQH9"),alt:"红包牛牛"}}),t._v(" "),i("form",{attrs:{action:"",methods:""}},[i("div",{staticClass:"forgot-item"},[i("p",{staticClass:"forgot-tel"},[i("i",{staticClass:"iconfont icon-mobile"}),t._v(" "),i("input",{directives:[{name:"model",rawName:"v-model",value:t.forgotInfo.mobile,expression:"forgotInfo.mobile"}],staticClass:"tel",attrs:{type:"number",name:"tel",placeholder:"请输入手机号"},domProps:{value:t.forgotInfo.mobile},on:{input:function(o){o.target.composing||t.$set(t.forgotInfo,"mobile",o.target.value)}}})])]),t._v(" "),i("div",{staticClass:"forgot-item"},[i("p",{staticClass:"forgot-verifycode"},[i("i",{staticClass:"iconfont icon-yanzhengma"}),t._v(" "),i("input",{directives:[{name:"model",rawName:"v-model",value:t.forgotInfo.verifycode,expression:"forgotInfo.verifycode"}],staticClass:"verifycode",attrs:{type:"number",placeholder:"请输入验证码"},domProps:{value:t.forgotInfo.verifycode},on:{input:function(o){o.target.composing||t.$set(t.forgotInfo,"verifycode",o.target.value)}}}),t._v(" "),t.forgotInfo.sendAuthCode?i("span",{staticClass:"auth_text",on:{click:t.getAuthCode}},[t._v("获取验证码")]):i("span",{staticClass:"auth_text",style:{background:"#fff",color:"#999"}},[t._v(t._s(t.forgotInfo.auth_time)+" 秒之重新发送")])])]),t._v(" "),i("button",{staticClass:"btn",attrs:{type:"submit"},on:{click:function(o){o.preventDefault(),t.handleForgotclick({Mobile:t.forgotInfo.mobile,verifycode:t.forgotInfo.verifycode})}}},[t._v("重设密码")])])])])},staticRenderFns:[]};var r=e("VU/8")(n,s,!1,function(t){e("JkyZ")},"data-v-303948dc",null);o.default=r.exports},JkyZ:function(t,o){}});
//# sourceMappingURL=31.90b9e69133718e322707.js.map