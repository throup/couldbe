"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[9],{3905:(e,t,n)=>{n.d(t,{Zo:()=>c,kt:()=>f});var r=n(7294);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?o(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):o(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,r,a=function(e,t){if(null==e)return{};var n,r,a={},o=Object.keys(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var p=r.createContext({}),s=function(e){var t=r.useContext(p),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},c=function(e){var t=s(e.components);return r.createElement(p.Provider,{value:t},e.children)},u="mdxType",d={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},m=r.forwardRef((function(e,t){var n=e.components,a=e.mdxType,o=e.originalType,p=e.parentName,c=l(e,["components","mdxType","originalType","parentName"]),u=s(n),m=a,f=u["".concat(p,".").concat(m)]||u[m]||d[m]||o;return n?r.createElement(f,i(i({ref:t},c),{},{components:n})):r.createElement(f,i({ref:t},c))}));function f(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var o=n.length,i=new Array(o);i[0]=m;var l={};for(var p in t)hasOwnProperty.call(t,p)&&(l[p]=t[p]);l.originalType=e,l[u]="string"==typeof e?e:a,i[1]=l;for(var s=2;s<o;s++)i[s]=n[s];return r.createElement.apply(null,i)}return r.createElement.apply(null,n)}m.displayName="MDXCreateElement"},4551:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>p,contentTitle:()=>i,default:()=>d,frontMatter:()=>o,metadata:()=>l,toc:()=>s});var r=n(7462),a=(n(7294),n(3905));const o={sidebar_position:1},i="ShowOrToString",l={unversionedId:"examples/create-a-page",id:"examples/create-a-page",title:"ShowOrToString",description:"If you are using the Cats library, you can define a Show type class for any data type.",source:"@site/docs/examples/create-a-page.md",sourceDirName:"examples",slug:"/examples/create-a-page",permalink:"/couldbe/docs/examples/create-a-page",draft:!1,editUrl:"https://github.com/throup/couldbe/tree/main/website/docs/examples/create-a-page.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1},sidebar:"tutorialSidebar",previous:{title:"Examples",permalink:"/couldbe/docs/category/examples"}},p={},s=[],c={toc:s},u="wrapper";function d(e){let{components:t,...n}=e;return(0,a.kt)(u,(0,r.Z)({},c,n,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("h1",{id:"showortostring"},"ShowOrToString"),(0,a.kt)("p",null,"If you are using the Cats library, you can define a ",(0,a.kt)("inlineCode",{parentName:"p"},"Show")," type class for any data type."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-scala"},"// Simplified definition.\n// See https://typelevel.org/cats/typeclasses/show.html for further details\ntrait Show[T]:\n  def show(t: T): String\n")),(0,a.kt)("p",null,"The ",(0,a.kt)("inlineCode",{parentName:"p"},"Show")," type provides a String representation of an object, guaranteed to be a deliberate choice instead of the default ",(0,a.kt)("inlineCode",{parentName:"p"},"toString()")," defined on every JVM object."),(0,a.kt)("p",null,"Sounds great! We should use it... if it is defined."),(0,a.kt)("p",null,"But maybe this is not ",(0,a.kt)("em",{parentName:"p"},"critical")," to our logic. Maybe we are logging, within a greater algortithm, and which to use the ",(0,a.kt)("inlineCode",{parentName:"p"},"Show")," representation when it is defined. But if it isn't, we can accept falling back to the default ",(0,a.kt)("inlineCode",{parentName:"p"},"toString()")," method."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-scala"},'// With Show\nlog.info("We diddled the doodle using " + Show.show(someObject))\n\n// Without Show\nlog.info("We diddled the doodle using " + someObject.toString)\n')),(0,a.kt)("p",null,"This sounds like an optional ",(0,a.kt)("inlineCode",{parentName:"p"},"given"),"!"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-scala"},"if (CouldBeGiven[Show[SomeObject]].isGiven) {\n  // use Show\n} else {\n  // use toString\n}\n")),(0,a.kt)("p",null,"In fact, the ",(0,a.kt)("inlineCode",{parentName:"p"},"couldbe-cats")," package provides a ready implementation of ",(0,a.kt)("inlineCode",{parentName:"p"},"ShowOrToString"),":"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-scala"},"trait ShowOrToString[-A: CouldHave[Show]]:\n  def s(a: A): String = CouldHave[Show, A].act(_.show(a))(() => a.toString)\n")),(0,a.kt)("p",null,"which allows you to simplify the logic to:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-scala"},'def yourFunction[A: ShowOrToString](a: A):\n  // ...\n  log.info("We diddled the doodle using " + ShowOrToString.s(someObject))\n  // ...\n')))}d.isMDXComponent=!0}}]);