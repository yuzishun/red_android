webpackJsonp([14],{"9RB0":function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=i("mvHQ"),s=i.n(n),a=i("pFYg"),o=i.n(a),c=(Boolean,Boolean,String,String,Object,Array,Boolean,Boolean,{name:"actionsheet",mounted:function(){var t=this;this.hasHeaderSlot=!!this.$slots.header,this.$nextTick(function(){t.$tabbar=document.querySelector(".weui-tabbar")})},props:{value:Boolean,showCancel:Boolean,cancelText:String,theme:{type:String,default:"ios"},menus:{type:[Object,Array],default:function(){return{}}},closeOnClickingMask:{type:Boolean,default:!0},closeOnClickingMenu:{type:Boolean,default:!0}},data:function(){return{hasHeaderSlot:!1,show:!1}},methods:{onMenuClick:function(t,e){"string"==typeof t?this.emitEvent("on-click-menu",e,t):"disabled"!==t.type&&"info"!==t.type&&(t.value||0===t.value?this.emitEvent("on-click-menu",t.value,t):(this.emitEvent("on-click-menu","",t),this.show=!1))},onClickingMask:function(){this.$emit("on-click-mask"),this.closeOnClickingMask&&(this.show=!1)},emitEvent:function(t,e,i){if("on-click-menu"===t&&!/.noop/.test(e)){var n=i;"object"===(void 0===n?"undefined":o()(n))&&(n=JSON.parse(s()(n))),this.$emit(t,e,n),this.$emit(t+"-"+e),this.closeOnClickingMenu&&(this.show=!1)}},fixIos:function(t){this.$el.parentNode&&-1!==this.$el.parentNode.className.indexOf("v-transfer-dom")||this.$tabbar&&/iphone/i.test(navigator.userAgent)&&(this.$tabbar.style.zIndex=t)}},watch:{show:function(t){var e=this;this.$emit("input",t),t?this.fixIos(-1):setTimeout(function(){e.fixIos(100)},200)},value:{handler:function(t){this.show=t},immediate:!0}},beforeDestroy:function(){this.fixIos(100)}}),l={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"vux-actionsheet"},[i("transition",{attrs:{name:"vux-actionsheet-mask"}},[i("div",{directives:[{name:"show",rawName:"v-show",value:t.show,expression:"show"}],staticClass:"weui-mask weui-mask_transparent",on:{click:t.onClickingMask}})]),t._v(" "),"android"===t.theme?i("div",{staticClass:"weui-skin_android"},[i("transition",{attrs:{name:"vux-android-actionsheet"}},[i("div",{directives:[{name:"show",rawName:"v-show",value:t.show,expression:"show"}],staticClass:"weui-actionsheet"},[i("div",{staticClass:"weui-actionsheet__menu"},t._l(t.menus,function(e,n){return i("div",{staticClass:"weui-actionsheet__cell",domProps:{innerHTML:t._s(e.label||e)},on:{click:function(i){t.onMenuClick(e,n)}}})}))])])],1):i("div",{staticClass:"weui-actionsheet",class:{"weui-actionsheet_toggle":t.show}},[i("div",{staticClass:"weui-actionsheet__menu"},[t.hasHeaderSlot?i("div",{staticClass:"weui-actionsheet__cell"},[t._t("header")],2):t._e(),t._v(" "),t._l(t.menus,function(e,n){return i("div",{staticClass:"weui-actionsheet__cell",class:"vux-actionsheet-menu-"+(e.type||"default"),domProps:{innerHTML:t._s(e.label||e)},on:{click:function(i){t.onMenuClick(e,n)}}})})],2),t._v(" "),t.showCancel?i("div",{staticClass:"weui-actionsheet__action",on:{click:function(e){t.emitEvent("on-click-menu","cancel")}}},[i("div",{staticClass:"weui-actionsheet__cell"},[t._v(t._s(t.cancelText||"取消"))])]):t._e()])],1)},staticRenderFns:[]};var u=i("VU/8")(c,l,!1,function(t){i("v7TV")},null,null).exports,r=i("mtWM"),d=i.n(r),h={components:{Actionsheet:u},data:function(){var t=window.localStorage.getItem("userName"),e=window.localStorage.getItem("myId");return{userName:t,show:!1,menus:{"title.noop":"附加图片",menu1:"拍一张",menu2:"从相册中选择"},txt:"",friendId:this.$route.params.id,myId:e,unfinish:!0}},methods:{click:function(t){console.log(t)},showPic:function(){this.show=!0},changeBtn:function(){this.unfinish=!1,""===this.txt&&(this.unfinish=!0)},finished:function(){var t=this;d.a.get("http://121.42.177.97:8080/red/user/set_friend.do?user_id="+this.myId+"&friend_id="+this.friendId+"&friend_text="+this.txt).then(function(e){window.localStorage.setItem("friend_text",t.txt),t.$router.back()}).catch(function(t){throw t})}}},v={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"profile",attrs:{id:"remark"}},[i("header",{attrs:{id:"header-title"}},[i("div",{staticClass:"cancel",on:{click:function(e){t.$router.back()}}},[t._v("取消")]),t._v(" "),i("button",{staticClass:"finished",attrs:{disabled:t.unfinish},on:{click:function(e){e.preventDefault(),t.finished(e)}}},[t._v("完成")]),t._v(" "),i("span",[t._v("设置备注和标签")])]),t._v(" "),i("div",{staticClass:"remark-cont"},[i("div",{staticClass:"weui-cells__title"},[t._v("描述")]),t._v(" "),i("div",{staticClass:"weui-cells"},[i("div",{staticClass:"weui-cell"},[i("div",{staticClass:"weui-cell__bd"},[i("input",{directives:[{name:"model",rawName:"v-model",value:t.txt,expression:"txt"}],staticClass:"weui-input",attrs:{type:"text",placeholder:"添加更多备注信息"},domProps:{value:t.txt},on:{input:[function(e){e.target.composing||(t.txt=e.target.value)},t.changeBtn]}})])])])])])},staticRenderFns:[]};var f=i("VU/8")(h,v,!1,function(t){i("vxc9")},"data-v-12b479f4",null);e.default=f.exports},v7TV:function(t,e){},vxc9:function(t,e){}});
//# sourceMappingURL=14.476b76d5047b947e0266.js.map