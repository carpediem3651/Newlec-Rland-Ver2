import Modal from '/js/lib/modal.js';

let menuList = document.querySelector(".menu-list");
let fileInput = menuList.querySelector("input[type=file]");

fileInput.oninput = function(e) {

	console.log("입력 완료");

	const files = event.target.files;
	let text = "";

	for (const file of files) {
		text += `${file.name}: ${file.type || "알 수 없음"}\n`;
	}

	let f = files[0];

	if (f.type.indexOf("image/") != 0) {

		let modal = new Modal({
			title: "알림",
			content: "이미지만 선택할 수 있습니다.",
			onok: function() {

			}
		});

		modal.show();

		return;
	}

	let reader = new FileReader();
	
	reader.addEventListener("load", () => {
		let img = fileInput.nextElementSibling;
		img.src = reader.result;
	})
	
	reader.readAsDataURL(f);

	console.log(text);
};


menuList.onclick = function(e) {

	let el = e.target;
	let isValidButtonClicked = el.classList.contains("btn-menu-del")
							|| el.classList.contains("btn-menu-add")
							|| el.classList.contains("btn-menu-reg")
							|| false;

	if (!isValidButtonClicked)
		return;

	if (el.classList.contains("btn-menu-del")) {
		e.preventDefault();
		//let modal = new Modal("제목이라요", "정말 삭제하시렵니꽈?!") --> ;	
		let modal = new Modal({
			title: "제목이라요",
			content: "정말 삭제하시렵니꽈?!",
			onok: function() {
				console.log("삭제되었습니다.!");
			}
		});

		modal.onok = function() {
			console.log("선택한 메뉴가 정상적으로 삭제되었습니다.!! 다시는 오지 않습니다.");
		};
		/*modal.title = "제모이랴요";
		modal.content = "정말 삭제하시렵니꽈~?";*/
		//modal.setTitle("제목이라요~");
		modal.show();
	}

	else if (el.classList.contains("btn-menu-add")) {
		el.parentElement.classList.add("slide-out");
	}

	else if (el.classList.contains("btn-menu-reg")) { // 메뉴를 등록하는 버튼 핸들러
		e.preventDefault();
		console.log("aaa");

		let form = document.querySelector(".menu-reg form");
		let korName = form.querySelector("input[name=kor-name]").value;
		let engName = form.querySelector("input[name=eng-name]").value;
		let price = form.querySelector("input[name=price]").value;

		console.log(korName, engName, price);

		let img = fileInput.files[0];

		let formData = new FormData();
		formData.append("korName", korName);
		formData.append("engName", engName);
		formData.append("price", price);
		formData.append("img", img);

		let request = new XMLHttpRequest();
		request.onload = () => {
			console.log("done test");
		};
		request.open("POST", "/api/menus");
		request.send(formData);
	}
};
