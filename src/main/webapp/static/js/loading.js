// var loadindex=layer.load(1);
// $(document).ready(function(){
// 	layer.close(loadindex);
// })
layui.use(["layer","jquery"],function(){
	var layer = layui.layer;
	var $ = layui.jquery;
	function _load(){
		return layer.load(1);
	}
})