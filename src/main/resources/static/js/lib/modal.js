export default class Modal{
	#title;
	#content;
	#onok;
	
	constructor(args){
		// 속성들
		this.#title = args.title || "확인";
		this.#content = args.content || "";	
		this.#onok = args.onok;
		
		this.root = this.createRootElement();
		
		this.host = document.createElement("div");
		document.body.append(this.host);
		
		const sheet = new CSSStyleSheet();
		sheet.replaceSync(this.getStyleText());
		
		const shadow = this.host.attachShadow({ mode: "open" });
		shadow.adoptedStyleSheets = [sheet];		
		shadow.appendChild(this.root);	
		
		const okButton = this.root.querySelector(".btn-ok");
		const cancelButton = this.root.querySelector(".btn-cancel");
		
		okButton.onclick = this.btnOkHandler.bind(this);
		cancelButton.onclick = this.btnCancelHandler.bind(this);
		
	}		
	
	btnOkHandler(){
		if(this.#onok)
			this.#onok();
			
		this.close();
	}
	
	btnCancelHandler(){
		this.close();
	}
	
	set title(title){
		this.#title = title;
		
		this.root.querySelector(".title").innerText = this.#title;
	}
	
	set content(content){
		this.#content = content;
		
		this.root.querySelector(".content").innerText = this.#content;
	}
	
	set onok(callback){
		this.#onok = callback;
	}
	
	show(){
		console.log("show");
		
		setTimeout(()=>{
			this.root.classList.add("show");			
		},20);
	}	
	
	close(){
		this.root.classList.remove("show");
		this.root.classList.add("close");
	}
	
	getStyleText(){
		return `
			h1{
				color:red;
			}
			.modal {
				background-color: #0009;
				position: fixed;
				left: 0;
				top: 0;
				width: 100vw;
				
				display: flex;
				justify-content: center;
				align-items: center;
				
				z-index: 1000;
				
				opacity: 0;
				height: 0vh;
				transition: opacity 1s;
			}
			
			.modal.show{
				opacity: 1;
				height: 100vh;
			}
			
			.modal.close{
				display: none;
			}
			
			.modal 
				.frame {
				background-color: #fff;
				transform: translateY(0px);
				border-radius: 10px;
				
				transition: transform 500ms ease-in-out;
			}
			
			.modal.show
				.frame{
					transform: translateY(-100px);
				}

			.modal
				.frame
					.title{
						padding: 5px 10px;
						background-color: var(--color-accent-1);
						color:var(--color-base-0);
						border-radius: 10px 10px 0 0;
					} 
				
			.modal
				.frame
					.content {
						padding: 10px 20px;		
					}
					
			.modal
				.frame
					.command {
						padding: 10px 20px;
						text-align: center;		
					}	
			
			.modal
				.frame
					.command
						button{
							cursor: pointer;
						}		
		`;
	}
	
	createRootElement(){
		let section = document.createElement("div");
		section.classList.add("modal");
		section.insertAdjacentHTML("beforeend",`			
				<div class="frame">
					<h1 class="title">${this.#title}</h1>
					<div class="content">
						${this.#content}
					</div>
					<div class="command">
						<button class="btn-ok btn btn-main pv:1 fs:-1">확인</button>
						<button class="btn-cancel btn btn-base-2 pv:1 fs:-1">취소</button>
					</div>
				</div>			
		`);
		
		return section;
	}
		
}