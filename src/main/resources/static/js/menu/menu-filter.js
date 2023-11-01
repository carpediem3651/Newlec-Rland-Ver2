export default{
	data(){
		return {
			x:10,
			y:20
		};	
	},
	template:`
		<section class="menu-filter">
            <h1>Rland Menu<span class="d:none">검색</span></h1>
    
            <nav class="category-filter">
                <h1 class="d:none">카테고리 검색 메뉴 목록</h1>
                <ul>
                    <li><a class="sm:deco current md:deco sm:icon-filter_list" href="">전체메뉴</a></li>
                    <li><a class="d:none md:d:inline" href="">커피</a></li>
                    <li><a class="d:none md:d:inline" href="">수제청</a></li>
                    <li><a class="d:none md:d:inline" href="">샌드위치</a></li>
                    <li><a class="d:none md:d:inline" href="">쿠키</a></li>
                </ul>
            </nav>
            <section class="query-filter">
                <h1 class="d:none">이름 검색 폼</h1>
                <form>
                    <fieldset>
                        <legend class="d:none">이름 검색</legend>
                        <input type="text" placeholder="메뉴 검색" >
                        <button type="submit" class="icon icon-find">검색</button>
                    </fieldset>
                </form>
            </section>
        </section>
	`
}