webpackJsonp([5],{ODCk:function(e,t){e.exports=function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"YYYY-MM-DD HH:mm:ss";if(!e)return"";"string"==typeof e&&(e=new Date(e.replace(/-/g,"/"))),"number"==typeof e&&(e=new Date(e));var n={"M+":e.getMonth()+1,"D+":e.getDate(),"h+":e.getHours()%12==0?12:e.getHours()%12,"H+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds(),"q+":Math.floor((e.getMonth()+3)/3),S:e.getMilliseconds()};for(var r in/(Y+)/.test(t)&&(t=t.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length))),/(E+)/.test(t)&&(t=t.replace(RegExp.$1,(RegExp.$1.length>1?RegExp.$1.length>2?"星期":"周":"")+{0:"日",1:"一",2:"二",3:"三",4:"四",5:"五",6:"六"}[e.getDay()+""])),n)new RegExp("("+r+")").test(t)&&(t=t.replace(RegExp.$1,1===RegExp.$1.length?n[r]:("00"+n[r]).substr((""+n[r]).length)));return t}},UNGY:function(e,t,n){"use strict";var r=n("fZjL"),i=n.n(r),a=n("oWtu"),o=n("pFYg"),l=n.n(o),c=n("+geU"),s=n("ghQH"),u=n.n(s);function h(e,t){for(var n in e)if(e.hasOwnProperty(n)&&!1===t.call(e[n],n,e[n]))break}function d(e){return e=(e=(e=String(e))?parseFloat(e.replace(/^0+/g,"")):"")||0,e+=""}function f(){for(var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:0,t=arguments[1],n=[],r=e;r<=t;r++)n.push(v(r));return n}function v(e){return(e=String(e)).length<2?"0"+e:e}function g(e,t){return e=parseFloat(e),2===(t=parseFloat(t))?function(e){return e%100!=0&&e%4==0||e%400==0}(e)?29:28:[4,6,9,11].indexOf(t)>=0?30:31}function m(e,t){return e.replace(/\{value\}/g,t)}function p(e){var t=document.createElement("div");return t.innerHTML=e,t.firstElementChild}function y(e){e&&e.parentNode.removeChild(e)}function S(e,t,n,r){var i=e.getFullYear(),a=t.getFullYear(),o=e.getMonth()+1,l=t.getMonth()+1,c=e.getDate(),s=t.getDate(),u=1,h=function(e,t){return e=parseFloat(e),2===(t=parseFloat(t))?function(e){return e%100!=0&&e%4==0||e%400==0}(e)?29:28:[4,6,9,11].indexOf(t)>=0?30:31}(n,r);return n===i&&r===o&&(u=c),n===a&&r===l&&(h=s),{minDay:u,maxDay:h}}var x="object"===("undefined"==typeof window?"undefined":l()(window)),D='<div class="dp-mask"></div>',_=200,w={year:["YYYY"],month:["MM","M"],day:["DD","D"],hour:["HH","H"],minute:["mm","m"]},Y=null,k=void 0,M=new Date,R={template:'<div class="dp-container">\n  <div class="dp-header">\n    <div class="dp-item dp-left vux-datetime-cancel" data-role="cancel">cancel</div>\n    <div class="dp-item vux-datetime-clear" data-role="clear"></div>\n    <div class="dp-item dp-right vux-datetime-confirm" data-role="confirm">done</div>\n  </div>\n  <div class="dp-content">\n    <div class="dp-item" data-role="year"></div>\n    <div class="dp-item" data-role="month"></div>\n    <div class="dp-item" data-role="day"></div>\n    <div class="dp-item" data-role="hour"></div>\n    <div class="dp-item" data-role="minute"></div>\n  </div>\n</div>',trigger:null,output:null,currentYear:M.getFullYear(),currentMonth:M.getMonth()+1,minYear:2e3,maxYear:2030,minHour:0,maxHour:23,hourList:null,minuteList:null,startDate:null,endDate:null,yearRow:"{value}",monthRow:"{value}",dayRow:"{value}",hourRow:"{value}",minuteRow:"{value}",format:"YYYY-MM-DD",value:M.getFullYear()+"-"+(M.getMonth()+1)+"-"+M.getDate(),onSelect:function(){},onConfirm:function(){},onClear:function(){},onShow:function(){},onHide:function(){},confirmText:"ok",clearText:"",cancelText:"cancel",destroyOnHide:!1,renderInline:!1,computeHoursFunction:null,computeDaysFunction:null,isOneInstance:!1};function H(e,t,n,r){return t=t.map(function(e){return e.value=e.value+"",e}),new c.a(e,{data:t,defaultValue:n+"",onSelect:r})}function F(e){var t,n=this;if(n.config={},n.value=e.value||"",h(R,function(t,r){n.config[t]=e[t]||r}),this.renderInline=n.config.renderInline,e.defaultSelectedValue&&!e.value&&(n.config.value=e.defaultSelectedValue),"string"==typeof this.config.startDate&&(this.config.startDate=new Date(this.config.startDate.replace(/-/g,"/"))),"string"==typeof this.config.endDate&&(this.config.endDate=new Date(this.config.endDate.replace(/-/g,"/"))),this.config.startDate&&!this.config.endDate&&(this.config.endDate=new Date("2030/12/31")),!this.config.startDate&&this.config.endDate&&(this.config.startDate=new Date(this.config.minYear+"/01/01")),this.reMakeData=!!this.config.startDate&&!!this.config.endDate,!this.renderInline){var r=n.config.trigger;this.triggerHandler=function(e){e.preventDefault(),n.show(n.value)},r&&x&&(r=n.trigger="string"==typeof(t=r)?document.querySelector(t):t,this.trigger=r,this.trigger&&this.trigger.addEventListener("click",this.triggerHandler,!1))}}F.prototype={_show:function(e){var t=this;t._setText(),t.container.style.display="block",this.renderInline&&t.container.classList.add("vux-datetime-view"),h(w,function(n){t[n+"Scroller"]&&t[n+"Scroller"].select(d(e[n]),!1)}),setTimeout(function(){t.container.style["-webkit-transform"]="translateY(0)",t.container.style.transform="translateY(0)"},0)},show:function(e){if(x){var t=this,n=t.config;if(n.isOneInstance){if(document.querySelector("#vux-datetime-instance"))return;t.willShow=!0}k=t;var r=t.valueMap=function(e,t){var n=e.split(/[^A-Za-z]+/),r=t.split(/\D+/);n.length!==r.length&&(r=u()(new Date,e).split(/\D+/));for(var i={},a=0;a<n.length;a++)n[a]&&(i[n[a]]=r[a]);return i}(n.format,e||n.value),i={};if(h(w,function(e,t){i[e]=1===t.length?r[t[0]]:r[t[0]]||r[t[1]]}),t.container)t._show(i);else{var a=t.container=p(n.template);n.isOneInstance&&(a.id="vux-datetime-instance"),t.renderInline?document.querySelector(t.config.trigger).appendChild(a):(document.body.appendChild(a),t.container.style.display="block"),h(w,function(e){var r=t.find("[data-role="+e+"]");if(void 0!==i[e]){var a=void 0;a="day"===e?t._makeData(e,d(i.year),d(i.month)):"hour"===e?t._makeData(e,d(i.year),d(i.month),d(i.day)):t._makeData(e),t[e+"Scroller"]=H(r,a,d(i[e]),function(r){setTimeout(function(){n.onSelect.call(t,e,r,t.getValue())},0),"year"!==e&&"month"!==e&&"day"!==e||t.hourScroller&&t._setHourScroller(t.yearScroller.value,t.monthScroller.value,t.dayScroller.value,t.hourScroller.value);var i=void 0;if("year"===e){var a=t.monthScroller?t.monthScroller.value:n.currentMonth;t._setMonthScroller(r,a),t.dayScroller&&(i=t.dayScroller.value,t._setDayScroller(r,a,i))}else if("month"===e){var o=t.yearScroller?t.yearScroller.value:n.currentYear;t.dayScroller&&(i=t.dayScroller.value,t._setDayScroller(o,r,i))}})}else y(r)}),t.renderText||t.renderInline||(t.config.confirmText&&(t.find("[data-role=confirm]").innerText=t.config.confirmText),t.config.cancelText&&(t.find("[data-role=cancel]").innerText=t.config.cancelText),t.config.clearText&&(t.find("[data-role=clear]").innerText=t.config.clearText),t.renderText=!0),this._show(i),t.find("[data-role=cancel]").addEventListener("click",function(e){e.preventDefault(),t.hide("cancel")},!1),t.find("[data-role=confirm]").addEventListener("click",function(e){e.preventDefault(),t.confirm()},!1),t.config.clearText&&t.find("[data-role=clear]").addEventListener("click",function(e){e.preventDefault(),t.clear()},!1)}this.renderInline||(x&&(Y||(Y=p(D),document.body.appendChild(Y),Y.addEventListener("click",function(){k&&k.hide("cancel")},!1)),Y.style.display="block",setTimeout(function(){Y&&(Y.style.opacity=.5)},0)),n.onShow.call(t))}},_setText:function(){},_makeData:function(e,t,n,r){var i,a,o=this.config,l=this.valueMap,c=w[e],s=[],u=void 0,h=void 0;if("year"===e){if(u=o.minYear,h=o.maxYear,this.reMakeData){var d=function(e,t){for(var n=e.getFullYear(),r=t.getFullYear(),i=[];n<=r;)i.push(n),n++;return{minYear:i[0],maxYear:i[i.length-1]}}(this.config.startDate,this.config.endDate);u=d.minYear,h=d.maxYear}}else if("month"===e){if(u=1,h=12,this.reMakeData){var p=function(e,t,n){var r=e.getFullYear(),i=t.getFullYear(),a=e.getMonth()+1,o=t.getMonth()+1,l=1,c=12;return n===r&&(l=a),n===i&&(c=o),{minMonth:l,maxMonth:c}}(this.config.startDate,this.config.endDate,1*this.yearScroller.value),y=p.minMonth,x=p.maxMonth;u=Math.max(u,y),h=Math.min(h,x)}}else if("day"===e){if(u=1,h=g(t,n),this.reMakeData){var D=S(this.config.startDate,this.config.endDate,1*this.yearScroller.value,1*this.monthScroller.value),_=D.minDay,Y=D.maxDay;u=Math.max(u,_),h=Math.min(h,Y)}}else"hour"===e?(u=this.config.minHour,h=this.config.maxHour):"minute"===e&&(u=0,h=59);for(var k=u;k<=h;k++){var M=void 0;if("year"===e)M=m(o.yearRow,k);else{var R=l[c[0]]?v(k):k;M=m(o[e+"Row"],R)}s.push({name:M,value:k})}if("hour"===e&&this.config.hourList&&(s=this.config.hourList.map(function(e){return{name:m(o.hourRow,e),value:Number(e)}})),"day"===e&&this.config.computeDaysFunction){var H=this.config.computeDaysFunction({year:t,month:n,min:u,max:h},f);H&&(s=H.map(function(e){return{name:m(o.dayRow,v(e)),value:Number(e)}}))}if("hour"===e&&this.config.computeHoursFunction){var F=(i=new Date(t+"/"+n+"/"+r),a=new Date,i.getFullYear()===a.getFullYear()&&i.getMonth()===a.getMonth()&&i.getDate()===a.getDate());s=this.config.computeHoursFunction(t+"-"+n+"-"+r,F,f).map(function(e){return{name:m(o.hourRow,e),value:Number(e)}})}return"minute"===e&&this.config.minuteList&&(s=this.config.minuteList.map(function(e){return{name:m(o.minuteRow,e),value:Number(e)}})),s},_setMonthScroller:function(e,t){if(this.monthScroller){var n=this;this.monthScroller.destroy();var r=n.find("[data-role=month]");n.monthScroller=H(r,n._makeData("month"),t,function(e){n.config.onSelect.call(n,"month",e,n.getValue());var t=n.yearScroller?n.yearScroller.value:n.config.currentYear;if(n.dayScroller){var r=n.dayScroller.value;n._setDayScroller(t,e,r)}n.yearScroller&&n.monthScroller&&n.hourScroller&&n._setHourScroller(t,e,n.dayScroller.value,n.hourScroller.value)})}},_setDayScroller:function(e,t,n){if(this.dayScroller){var r=this,i=g(e,t);n>i&&(n=i),r.dayScroller.destroy();var a=r.find("[data-role=day]");r.dayScroller=H(a,r._makeData("day",e,t),n,function(n){r.config.onSelect.call(r,"day",n,r.getValue()),r.hourScroller&&r._setHourScroller(e,t,n,r.hourScroller.value)})}},_setHourScroller:function(e,t,n,r){if(this.hourScroller){var i=this;i.hourScroller.destroy();var a=i.find("[data-role=hour]");i.hourScroller=H(a,i._makeData("hour",e,t,n),r||"",function(e){i.config.onSelect.call(i,"hour",e,i.getValue())})}},find:function(e){return this.container.querySelector(e)},hide:function(e){if(this.container){var t=this;t.container.style.removeProperty("transform"),t.container.style.removeProperty("-webkit-transform"),setTimeout(function(){t.container&&(t.container.style.display="none")},300),Y&&(Y.style.opacity=0,setTimeout(function(){Y&&(Y.style.display="none")},_)),t.config.onHide.call(t,e),t.config.destroyOnHide&&setTimeout(function(){t.destroy()},500)}},select:function(e,t){this[e+"Scroller"].select(t,!1)},destroy:function(){this.trigger&&this.trigger.removeEventListener("click",this.triggerHandler,!1),this.config.isOneInstance||this.willShow||(y(Y),Y=null),y(this.container),this.container=null},getValue:function(){var e=this,t=e.config.format;return h(w,function(n,r){!function(e,n,r){if(e){var i=e.value;n&&(t=t.replace(new RegExp(n,"g"),v(i))),r&&(t=t.replace(new RegExp(r,"g"),d(i)))}}(e[n+"Scroller"],r[0],r[1])}),t},confirm:function(){var e=this.getValue();this.value=e,!1!==this.config.onConfirm.call(this,e)&&this.hide("confirm")},clear:function(){var e=this.getValue();!1!==this.config.onClear.call(this,e)&&this.hide("clear")}};var b=F,T=n("rHil"),$=n("kbG3"),C=n("KRg4"),V=n("ODCk"),E=n.n(V),L=(C.a,T.a,$.a,a.a,String,String,String,String,String,Number,Number,String,String,String,String,String,String,String,String,Boolean,Number,Number,String,String,String,Function,Boolean,Array,Array,Boolean,String,Function,Function,{name:"datetime",mixins:[C.a],components:{Group:T.a,InlineDesc:$.a,Icon:a.a},props:{format:{type:String,default:"YYYY-MM-DD"},title:String,value:{type:String,default:""},inlineDesc:String,placeholder:String,minYear:Number,maxYear:Number,confirmText:String,cancelText:String,clearText:String,yearRow:{type:String,default:"{value}"},monthRow:{type:String,default:"{value}"},dayRow:{type:String,default:"{value}"},hourRow:{type:String,default:"{value}"},minuteRow:{type:String,default:"{value}"},required:{type:Boolean,default:!1},minHour:{type:Number,default:0},maxHour:{type:Number,default:23},startDate:{type:String,validator:function(e){return!e||10===e.length}},endDate:{type:String,validator:function(e){return!e||10===e.length}},valueTextAlign:String,displayFormat:Function,readonly:Boolean,hourList:Array,minuteList:Array,show:Boolean,defaultSelectedValue:String,computeHoursFunction:Function,computeDaysFunction:Function},created:function(){this.isFirstSetValue=!1,this.currentValue=this.value},data:function(){return{currentShow:!1,currentValue:null,valid:!0,errors:{}}},mounted:function(){var e=this,t=this.uuid;this.$el.setAttribute("id","vux-datetime-"+t),this.readonly||this.$nextTick(function(){e.render(),e.show&&e.$nextTick(function(){e.picker&&e.picker.show(e.currentValue)})})},computed:{pickerOptions:function(){var e=this,t={trigger:"#vux-datetime-"+this.uuid,format:this.format,value:this.currentValue,output:".vux-datetime-value",confirmText:e.getButtonText("confirm"),cancelText:e.getButtonText("cancel"),clearText:e.clearText,yearRow:this.yearRow,monthRow:this.monthRow,dayRow:this.dayRow,hourRow:this.hourRow,minuteRow:this.minuteRow,minHour:this.minHour,maxHour:this.maxHour,startDate:this.startDate,endDate:this.endDate,hourList:this.hourList,minuteList:this.minuteList,defaultSelectedValue:this.defaultSelectedValue,computeHoursFunction:this.computeHoursFunction,computeDaysFunction:this.computeDaysFunction,onSelect:function(t,n,r){e.picker&&e.picker.config.renderInline&&(e.$emit("input",r),e.$emit("on-change",r))},onConfirm:function(t){e.currentValue=t},onClear:function(t){e.$emit("on-clear",t)},onHide:function(t){e.currentShow=!1,e.$emit("update:show",!1),e.validate(),e.$emit("on-hide",t),"cancel"===t&&e.$emit("on-cancel"),"confirm"===t&&e.$emit("on-confirm")},onShow:function(){e.currentShow=!0,e.$emit("update:show",!0),e.$emit("on-show")}};return this.minYear&&(t.minYear=this.minYear),this.maxYear&&(t.maxYear=this.maxYear),t},firstError:function(){var e=i()(this.errors)[0];return this.errors[e]},labelClass:function(){return{"vux-cell-justify":"justify"===this.$parent.labelAlign||"justify"===this.$parent.$parent.labelAlign}}},methods:{getButtonText:function(e){return"cancel"===e&&this.cancelText?this.cancelText:"confirm"===e&&this.confirmText?this.confirmText:this.$el.getAttribute("data-"+e+"-text")},render:function(){var e=this;this.$nextTick(function(){e.picker&&e.picker.destroy(),e.picker=new b(e.pickerOptions)})},validate:function(){if(!this.currentValue&&this.required)return this.valid=!1,void(this.errors.required="必填");this.valid=!0,this.errors={}}},watch:{readonly:function(e){e?this.picker&&this.picker.destroy():this.render()},show:function(e){e!==this.currentShow&&(e?this.picker&&this.picker.show(this.currentValue):this.picker&&this.picker.hide(this.currentValue))},currentValue:function(e,t){this.$emit("input",e),this.isFirstSetValue?this.$emit("on-change",e):(this.isFirstSetValue=!0,t&&this.$emit("on-change",e)),this.validate()},startDate:function(){this.render()},endDate:function(){this.render()},format:function(e){this.currentValue&&(this.currentValue=E()(this.currentValue,e)),this.render()},value:function(e){this.readonly||this.picker&&this.picker.config.renderInline?this.currentValue=e:this.currentValue!==e&&(this.currentValue=e,this.render())}},beforeDestroy:function(){this.picker&&this.picker.destroy()}}),I={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("a",{staticClass:"vux-datetime weui-cell",class:{"weui-cell_access":!e.readonly},attrs:{"data-cancel-text":"取消","data-confirm-text":"确定",href:"javascript:"}},[e._t("default",[n("div",[e._t("title",[n("p",{class:e.labelClass,style:{width:e.$parent.labelWidth,textAlign:e.$parent.labelAlign,marginRight:e.$parent.labelMarginRight},domProps:{innerHTML:e._s(e.title)}})]),e._v(" "),e.inlineDesc?n("inline-desc",[e._v(e._s(e.inlineDesc))]):e._e()],2),e._v(" "),n("div",{staticClass:"weui-cell__ft vux-cell-primary vux-datetime-value",style:{textAlign:e.valueTextAlign}},[!e.currentValue&&e.placeholder?n("span",{staticClass:"vux-cell-placeholder"},[e._v(e._s(e.placeholder))]):e._e(),e._v(" "),e.currentValue?n("span",{staticClass:"vux-cell-value"},[e._v(e._s(e.displayFormat?e.displayFormat(e.currentValue):e.currentValue))]):e._e(),e._v(" "),n("icon",{directives:[{name:"show",rawName:"v-show",value:!e.valid,expression:"!valid"}],staticClass:"vux-input-icon",attrs:{type:"warn",title:e.firstError}})],1)])],2)},staticRenderFns:[]};var N=n("VU/8")(L,I,!1,function(e){n("eyyZ")},null,null);t.a=N.exports},UYm0:function(e,t){},ZFrJ:function(e,t){},eyyZ:function(e,t){},fEiY:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=n("UNGY"),i=(r.a,{components:{Datetime:r.a},methods:{change:function(e){console.log("change",e)}},data:function(){return{value:"2017年",startDate:"2016-01-01",endDate:"2018-12-31"}}}),a={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"grap"},[n("header",{attrs:{id:"sub-header"}},[n("div",{staticClass:"center"},[n("div",{staticClass:"iconfont icon-fanhui",on:{click:function(t){e.$router.back()}}},[n("span",[e._v("返回")])]),e._v(" "),n("span",[e._v("已抢红包")])])]),e._v(" "),n("section",[e._m(0),e._v(" "),n("div",{staticClass:"datatime"},[n("datetime",{attrs:{"start-date":e.startDate,"end-date":e.endDate,format:"YYYY年",title:"","confirm-text":"完成"},on:{"on-change":e.change},model:{value:e.value,callback:function(t){e.value=t},expression:"value"}})],1),e._v(" "),e._m(1)])])},staticRenderFns:[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"self-header"},[r("img",{attrs:{src:n("c5Or"),alt:""}}),e._v(" "),r("h3",{staticClass:"title"},[e._v("Andy共收到")]),e._v(" "),r("p",{staticClass:"money"},[e._v("5432.1")]),e._v(" "),r("h4",[e._v("13")]),e._v(" "),r("p",[e._v("收到红包")])])},function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"weui-cells red-list"},[n("div",{staticClass:"weui-cell"},[n("div",{staticClass:"weui-cell__bd"},[n("h5",[e._v("广告词红包")]),e._v(" "),n("span",{staticClass:"num"},[e._v("1/23")])]),e._v(" "),n("div",{staticClass:"weui-cell__ft"},[e._v("8.00元")])]),e._v(" "),n("div",{staticClass:"weui-cell"},[n("div",{staticClass:"weui-cell__bd"},[n("h5",[e._v("广告词红包")]),e._v(" "),n("span",{staticClass:"num"},[e._v("1/23")])]),e._v(" "),n("div",{staticClass:"weui-cell__ft"},[e._v("8.00元")])]),e._v(" "),n("div",{staticClass:"weui-cell"},[n("div",{staticClass:"weui-cell__bd"},[n("h5",[e._v("普通红包")]),e._v(" "),n("span",{staticClass:"num"},[e._v("1/23")])]),e._v(" "),n("div",{staticClass:"weui-cell__ft"},[e._v("8.00元")])])])}]};var o=n("VU/8")(i,a,!1,function(e){n("ZFrJ"),n("UYm0")},"data-v-6333e386",null);t.default=o.exports},ghQH:function(e,t){e.exports=function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"YYYY-MM-DD HH:mm:ss",n={"M+":e.getMonth()+1,"D+":e.getDate(),"h+":e.getHours()%12==0?12:e.getHours()%12,"H+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds(),"q+":Math.floor((e.getMonth()+3)/3),S:e.getMilliseconds()};for(var r in/(Y+)/.test(t)&&(t=t.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length))),/(E+)/.test(t)&&(t=t.replace(RegExp.$1,(RegExp.$1.length>1?RegExp.$1.length>2?"星期":"周":"")+{0:"日",1:"一",2:"二",3:"三",4:"四",5:"五",6:"六"}[e.getDay()+""])),n)new RegExp("("+r+")").test(t)&&(t=t.replace(RegExp.$1,1===RegExp.$1.length?n[r]:("00"+n[r]).substr((""+n[r]).length)));return t}}});
//# sourceMappingURL=5.ba51f4a5c677bfeada2d.js.map