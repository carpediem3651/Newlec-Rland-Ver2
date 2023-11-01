import MenuFilter from './menu-filter.js';
import MenuList from './menu-card-list.js';


// 컨트롤러
const { createApp } = Vue;

//let csrfToken = document.querySelector("#csrf").content;

createApp({    
	components:{
		"menu-filter":MenuFilter,
		"menu-list":MenuList
	},
    // 옵션들
    data(){
        return {
        };
    },
})
.mount("#main");

//let x = 30;

