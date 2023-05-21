"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[671],{3905:(e,t,r)=>{r.d(t,{Zo:()=>s,kt:()=>b});var n=r(7294);function a(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function i(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function o(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?i(Object(r),!0).forEach((function(t){a(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):i(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function l(e,t){if(null==e)return{};var r,n,a=function(e,t){if(null==e)return{};var r,n,a={},i=Object.keys(e);for(n=0;n<i.length;n++)r=i[n],t.indexOf(r)>=0||(a[r]=e[r]);return a}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(n=0;n<i.length;n++)r=i[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(a[r]=e[r])}return a}var p=n.createContext({}),c=function(e){var t=n.useContext(p),r=t;return e&&(r="function"==typeof e?e(t):o(o({},t),e)),r},s=function(e){var t=c(e.components);return n.createElement(p.Provider,{value:t},e.children)},u="mdxType",d={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},m=n.forwardRef((function(e,t){var r=e.components,a=e.mdxType,i=e.originalType,p=e.parentName,s=l(e,["components","mdxType","originalType","parentName"]),u=c(r),m=a,b=u["".concat(p,".").concat(m)]||u[m]||d[m]||i;return r?n.createElement(b,o(o({ref:t},s),{},{components:r})):n.createElement(b,o({ref:t},s))}));function b(e,t){var r=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var i=r.length,o=new Array(i);o[0]=m;var l={};for(var p in t)hasOwnProperty.call(t,p)&&(l[p]=t[p]);l.originalType=e,l[u]="string"==typeof e?e:a,o[1]=l;for(var c=2;c<i;c++)o[c]=r[c];return n.createElement.apply(null,o)}return n.createElement.apply(null,r)}m.displayName="MDXCreateElement"},9881:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>p,contentTitle:()=>o,default:()=>d,frontMatter:()=>i,metadata:()=>l,toc:()=>c});var n=r(7462),a=(r(7294),r(3905));const i={sidebar_position:1},o="Getting started",l={unversionedId:"intro",id:"intro",title:"Getting started",description:"Packages for couldbe are published to Github's maven registry. To include in your project, add the appropriate dependencies to your build.sbt:",source:"@site/docs/intro.md",sourceDirName:".",slug:"/intro",permalink:"/couldbe/docs/intro",draft:!1,editUrl:"https://github.com/throup/couldbe/tree/main/website/docs/intro.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1},sidebar:"tutorialSidebar",next:{title:"Examples",permalink:"/couldbe/docs/category/examples"}},p={},c=[],s={toc:c},u="wrapper";function d(e){let{components:t,...r}=e;return(0,a.kt)(u,(0,n.Z)({},s,r,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("h1",{id:"getting-started"},"Getting started"),(0,a.kt)("p",null,"Packages for ",(0,a.kt)("strong",{parentName:"p"},"couldbe")," are published to ",(0,a.kt)("a",{parentName:"p",href:"https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry"},"Github's maven registry"),". To include in your project, add the appropriate dependencies to your ",(0,a.kt)("inlineCode",{parentName:"p"},"build.sbt"),":"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-sbt"},'libraryDependencies += "eu.throup" %% "couldbe" % "<latest version>"\n')),(0,a.kt)("p",null,"The available packages are:"),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("inlineCode",{parentName:"li"},"couldbe"),": umbrella meta package to pull in ",(0,a.kt)("inlineCode",{parentName:"li"},"core")," and ",(0,a.kt)("inlineCode",{parentName:"li"},"cats")),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("inlineCode",{parentName:"li"},"couldbe-core"),": minimal implementation to allow basic functionality"),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("inlineCode",{parentName:"li"},"couldbe-cats"),": extra definitions and functionality for those using the ",(0,a.kt)("a",{parentName:"li",href:"https://typelevel.org/cats/"},"Cats")," library"),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("inlineCode",{parentName:"li"},"couldbe-testsupport"),": extra definitions and functionality to support writing tests")),(0,a.kt)("p",null,"You will also need to configure access to ",(0,a.kt)("a",{parentName:"p",href:"https://docs.github.com/en/packages/learn-github-packages/introduction-to-github-packages"},"Github's package registry"),". There are many ways to do this, but the simplest is to add ",(0,a.kt)("a",{parentName:"p",href:"https://github.com/djspiewak/sbt-github-packages"},"sbt-github-packages")," to your ",(0,a.kt)("inlineCode",{parentName:"p"},"project/plugin.sbt"),":"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-sbt"},'addSbtPlugin("com.codecommit" % "sbt-github-packages" % "0.5.3")\n')))}d.isMDXComponent=!0}}]);