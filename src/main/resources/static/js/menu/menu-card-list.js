export default {    
    // 옵션들
    data(){
		console.log("test");
        return { 
            x:50,
            y:`<span style="color:red;">하하</span>`,
            query:"",
            list:[]
        };
    },
    template:`
    	<section class="menu-card-list">
            <h1 class="d:none">메뉴 목록</h1>
            <div class="content">
                <section class="menu-card" :class="{'soldout':m.amount==0}" v-for="(m, index) in list">
                    <h1>
                        <a class="heading-3" :href="'detail.html?id='+m.id" v-text="m.korName"></a></h1>
                    <h2 class="heading-2 font-weight:normal color:base-5" v-text="m.engName"></h2>
                    <div class="price-block d:flex align-items:flex-end"><span class="font-weight:bold" v-text="m.price"></span>원<span class="soldout-msg ml:auto mr:auto md:d:none">SOLD OUT</span></div>
                    <div class="img-block">
                        <a :href="'detail.html?id='+m.id"><img :src="'/image/menu/product/'+m.img"></a>
                    </div>
                    <div class="like-block d:flex justify-content:flex-end">
                        <a class="icon icon-color:base-4" 
                            :class="{'icon-heart':!m.isLike, 'icon-heart-fill':m.isLike}" 
                            href=""
                            @click.prevent="heartClickHandler(index)">좋아요</a>
                        <span class="font-weight:bold ml:1" v-text="m.likeCount"></span>
                    </div>
                    <div class="pay-block d:flex">
                        <a class="icon md:icon:none icon-cart icon-color:base-0 color:base-0 btn-type:icon btn-cart md:btn-type:text" href="">담기</a>
                        <a class="icon md:icon:none icon-card icon-color:base-0 color:base-0 btn-type:icon btn-card md:btn-type:text" href="">주문하기</a>
                    </div>
                </section>
            </div>
        </section>
    `,
    beforeCreate(){
		console.log("beforeCreate");
	},
	created(){
		console.log("created");
	},
	beforeMount(){
		console.log("beforeMount");
	},
	mounted(){		
		this.bind();
		console.log("mounted");
	},
	beforeUpdate(){
		console.log("beforeUpdate");
	},
	updated(){
		console.log("updated");
	},
	beforeUnmount(){
		console.log("beforeUnmount");
	},
	unmounted(){
		console.log("unmounted");
	},
	errorCaptured(){
		console.log("errorCaptured");
	},
	renderTracked(){
		console.log("renderTracked");
	},
	renderTriggered(){
		console.log("renderTriggered");
	},
	activated(){
		console.log("activated");
	},
	deactivated(){
		console.log("deactivated");
	},
	serverPrefetch(){
		console.log("serverPrefetch");
	},
    methods:{
		async bind(){
			// e.preventDefault(); // DOM 기능(X)

            console.log(this.query);

            let response = await fetch(`http://localhost:8080/api/menus?q=${this.query}`);
            let list = await response.json();

            this.list = list;
		},
        findHandler(){
            this.bind();
        },
        async heartClickHandler(index){
            console.log("heart love");
            console.log(index);

            let curMenu = this.list[index];
            
            if(!curMenu.isLike){ 
                // 좋아요를 추가하는 요청
                let url = `http://localhost:8080/api/likes`; //POST

                let likeData = {
                    menuId:curMenu.id,
                    memberId:1 // newlec 사용자 아이디                
                };

                console.log(JSON.stringify(likeData));

                let response = await fetch(url,{
                                    method:"POST",
                                    headers: {
                                        "Content-Type": "application/json",
                                        // 'Content-Type': 'application/x-www-form-urlencoded',
                                        "X-CSRF-TOKEN":csrfToken
                                    },
                                    body: JSON.stringify(likeData)
                                });

                let newOne = await response.json();

                console.log(newOne);
                curMenu.isLike = true;
                curMenu.likeCount++;
            }
            else{ // 좋아요를 제거하는 요청
                let url = `http://localhost:8080/api/likes/${curMenu.id},1`; //DELETE

                let response = await fetch(url,{method:"DELETE"});
                let result = await response.json();

                console.log(result);
                
                if(!result)
                    return;

                curMenu.isLike = false;
                curMenu.likeCount--;
                
            }
            //this.list[index].isLike = !this.list[index].isLike;
        },
        bb(){}
    }
};
//let x = 30;

