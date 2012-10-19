<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://pages.aifcdn.com/js/jquery-1.7.1/js/jquery.js"></script>
<title>Stanford Chinese segment</title>
<script type="text/javascript">
    function clean(id){
        $("#"+id).val("");
    }

    function segment(){
        var text = $("#text").val();
        var dictOnReload = $("#dictOnReload").attr("checked")=="checked";
        $.post("ons","text="+text+"&reload="+dictOnReload ,function(data) {
              if(data !=null){
                  $("#onlineResult").val(data);
              }
            },"html");

        $.post("offs","text="+text ,function(data) {
              if(data !=null){
                  $("#offlineResult").val(data);
              }
            },"html");
    }

    function trainDict(){
        var dicts = $("#dicts").val();
        var dictOffReload = $("#dictOffReload").attr("checked")=="checked";
        $.post("dt","dicts="+dicts+"&reload="+dictOffReload ,function(data) {
            segment();
          },"html");


    }

    function trainClassify(){
        var sentence = $("#sentence").val();
        var trainTimes = $("#trainTimes").val();
        var classifyOffReload = $("#classifyOffReload").attr("checked")=="checked";
        $.post("ct","sentence="+sentence+"&trainTimes="+trainTimes+"&reload="+classifyOffReload ,function(data) {
            segment();
          },"html");

    }
</script>
</head>
<body>
    <div align="center">
        <div align="center"><h2>Stanford Chinese Segmente</h2></div>
        <div id="segmenter">
            <form action="cs" method="get">
                <div style= "margin:0 auto;display:inline-block;width:1200px">
                    <div style="float: left">
                        <textarea name="text" id="text" rows="4" cols="117" ></textarea>
                        <div align="right">
                            <input type="checkbox" id="dictOnReload"/>online reload
                             &nbsp;&nbsp;<input type="button" value="segment"  onclick="segment();">
                             &nbsp;&nbsp;<input type="button" value="clean"  onclick="clean('text');">
                        </div>
                    </div>
                </div>

                <div style= "margin:0 auto;display:inline-block;width:1200px;">
                    <div style="float: left">
                        <div align="left" style="color: red">Online result:</div>
                        <textarea name="onlineResult" id="onlineResult" rows="4" cols="117" ></textarea>
                    </div>
                    <div style="float: left">
                        <div align="left" style="color: red">Offline result:</div>
                        <textarea name="offlineResult" id="offlineResult" rows="4" cols="117" ></textarea>
                    </div>

                </div>

            </form>
        </div>

        <div id="trainer" style= "margin:0 auto;display:inline-block;width:1200px;">
            <div style="float: left">
                 <form action="" method="post">
                     <div style="color: red">One word per line!</div>
                    <textarea name="dicts" id="dicts" rows="12" cols="15" ></textarea>
                    <div>
                        <input type="checkbox" id="dictOffReload" checked="checked"/>reload
                        <input type="button" value="train dict" onclick="trainDict();">
                    </div>
                </form>
            </div>

            <div style="float: left">
                 <form action="" method="post">
                     <div style="color: red">Segmented sentence to train:</div>
                    <textarea name="sentence" id="sentence" rows="12" cols="100" ></textarea>
                    <div align="right">
                        <input type="checkbox" id="classifyOffReload" checked="checked"/>reload
                        &nbsp;&nbsp;&nbsp;&nbsp;times: <select id="trainTimes" name="trainTimes" title="train times" >
                            <option value ="1">1</option>
                            <option value ="2">2</option>
                            <option value ="3">3</option>
                            <option value ="4">4</option>
                            <option value ="5">5</option>
                        </select>
                        &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="train classify" onclick="trainClassify();">
                        &nbsp;&nbsp;<input type="button" value="clean"  onclick="clean('sentence');">
                    </div>
                </form>
            </div>

        </div>
    </div>
</body>
</html>