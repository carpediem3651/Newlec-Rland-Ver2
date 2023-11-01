window.addEventListener("load", function(){
	//let btnSubmit = document.querySelector(".form-section input[type=submit]");
	let categoryFilterSection = document.querySelector(".category-filter");
	let categoryUL = categoryFilterSection.querySelector("ul");
	
	let formSection = document.querySelector(".query-filter");	
	let findButton = formSection.querySelector(".btn-find");
	let queryInput = formSection.querySelector(".input-query"); // document에서 입력객체 얻기
	
	let content = document.querySelector(".menu-card-list .content");
	
	let currentCategory = categoryUL.querySelector("li:first-child a"); // 첫번째 li 자식의 a 의 객체;
	//let currentCategory = categoryUL.firstChild.firstChild;
	
	content.onclick =(e)=>{
		e.preventDefault();
		let el = e.target;
		
		if(!el.classList.contains("btn-like")) // .btn-like 가 아니면
			return;
		
		console.log("like")

		// /api/menus?c=${el.dataset.id}
		// /api/likes/3
	};
	
	
	function bind(list){
		// 데이터를 요청하고 응답을 기다리는 중...
		//let list = JSON.parse(text);
		//alert(request.responseType);
		
		content.innerHTML = "";
		// section을 위한 DOM 객체를 직접 생성해서 append 한다.
		for(let m of list){
			
			let iconHeart = m.isLike?"icon-heart-fill":"icon-heart";
			let template = `
				<section class="menu-card">
	                <h1>
	                    <a class="heading-3" href="detail.html">${m.korName}</a></h1>
	                <h2 class="heading-2 font-weight:normal color:base-5">${m.engName}</h2>
	                <div class="price-block d:flex align-items:flex-end">
	                	<span class="font-weight:bold">${m.price}</span>원<span class="soldout-msg ml:auto mr:auto md:d:none">SOLD OUT</span></div>
	                <div class="img-block">
	                    <a href="detail?id=${m.id}"><img src="/image/menu/product/${m.img}"></a>
	                </div>
	                <div class="like-block d:flex justify-content:flex-end">
	                    <a class="icon icon-color:base-4 ${iconHeart}" href="">좋아요</a>
	                    <span class="font-weight:bold ml:1">${m.likeCount}</span>
	                </div>
	                <div class="pay-block d:flex">
	                    <a class="icon md:icon:none icon-cart icon-color:base-0 color:base-0 btn-type:icon btn-cart md:btn-type:text" href="">담기</a>
	                    <a class="icon md:icon:none icon-card icon-color:base-0 color:base-0 btn-type:icon btn-card md:btn-type:text" href="">주문하기</a>
	                </div>
	            </section>
				`;
				
			content.insertAdjacentHTML("beforeend", template);
		}
	}
	
	categoryUL.onclick = async function(e){
		e.preventDefault();
		
		let el = e.target; 
		//console.log(e.tagName);
		if(el.tagName != "A")
			return;
		
		
		console.log(el.className);
		//el.className += " current"
		currentCategory.classList.remove("current");
		el.classList.add("current");
		currentCategory = el;		
		
		// 업무로직
		console.log(el.dataset.id);
		
		// /api/menus?c=
		let response = await fetch(`/api/menus?c=${el.dataset.id}`);
		let json = await response.json();
		bind(json);
		
		/*let request = new XMLHttpRequest();
		request.open("GET", `/api/menus?c=${el.dataset.id}`, true);
		request.onload = function(){
			bind(request.responseText);
		};
		request.send();*/
		
	}
	
	findButton.onclick = async function(e){
		e.preventDefault();
		
		let query = queryInput.value; // 입력 객체의 속성에서 입력 값을 얻어오기
		
		let response = await fetch(`/api/menus?q=${query}`);
		let json = await response.json();
		bind(json);
		
		/*let request = new XMLHttpRequest();
		request.open("GET", `/api/menus?q=${query}`, true);
		request.onload = function(){
			bind(request.responseText);
		};
		request.send();		*/
		
		console.log("나 그 다음 순서인데...");		
		
		// /api/menus?q=...
		/*
		XmlHttpRequest 	-1 : Callback
		Fetch API 		-2 : Promise
				
		비동기방식
		Callback -> Promise*/
		
	}
});