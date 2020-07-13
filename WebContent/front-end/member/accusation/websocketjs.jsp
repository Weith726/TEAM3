<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	
	
	<div class="wrapper">
		<div class="chat-box">
			<div class="chat-head" >
				<h2>Chat Box</h2>
				<img src="https://maxcdn.icons8.com/windows10/PNG/16/Arrows/angle_down-16.png" title="Expand Arrow" width="16" >
			</div>
			<div class="chat-body" style="display:none;">
				<div class="msg-insert" id="messagesArea">
  <div class="msg-send" id="msgsend"> Send message </div>
  <div class="msg-receive" id="msgrec"> Received message </div>
</div>
				<div class="chat-text" >
					<textarea placeholder="Send" id="message" onkeydown="if (event.keyCode == 13) sendMessage();"></textarea>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	<h3 id="statusOutput" class="statusOutput" style="display:none;"></h3>
	
	<div id="messagesArea" class="panel message-area" style="display:none;"></div>
	<div id="row"></div>
	<div class="panel input-area">
		<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" style="display:none;"/> 
		<input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage();" style="display:none;"/> 
		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" style="display:none;"/> 
		<input type="button" id="disconnect" class="button" value="Discont" onclick="disconnect();" style="display:none;"/>
	</div>
	
	
	 <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>
	  
	  
	<script>
	
	
	$(function(){
		var arrow = $('.chat-head img');
		var textarea = $('.chat-text textarea');

		arrow.on('click', function(){
			var src = arrow.attr('src');

			$('.chat-body').slideToggle('fast');
			if(src == 'https://maxcdn.icons8.com/windows10/PNG/16/Arrows/angle_down-16.png'){
				arrow.attr('src', 'https://maxcdn.icons8.com/windows10/PNG/16/Arrows/angle_up-16.png');
				connect();
			}
			else{
				arrow.attr('src', 'https://maxcdn.icons8.com/windows10/PNG/16/Arrows/angle_down-16.png');
				disconnect();
			}
		});

		textarea.keypress(function(event) {
			var $this = $(this);

			if(event.keyCode == 13){
				var msg = $this.val();
				$this.val('');
				$('.msg-insert').prepend("<div class='msg-send'>"+msg+"</div>");
				}
		});

	});
	
	
	
	
			var MyPoint = "/FriendWS/${memName}";
			var host = window.location.host;
			var path = window.location.pathname;
			var webCtx = path.substring(0, path.indexOf('/', 1));
			var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
			var statusOutput = document.getElementById("statusOutput");
			var messagesArea = document.getElementById("messagesArea");
			var self = '${memName}';
			var webSocket;
			
			function connect() {
				// create a websocket
				webSocket = new WebSocket(endPointURL);
			
				var messagesArea = document.getElementById("messagesArea");
				var msgsend = document.getElementById("msgsend");
				var msgrec = document.getElementById("msgrec");
				messagesArea.style.display="";	
				
				webSocket.onopen = function(event) {
					console.log("Connect Success!");
				};	
				
				webSocket.onmessage = function(event) {
					var jsonObj = JSON.parse(event.data);
					if ("open" === jsonObj.type) {
						refreshFriendList(jsonObj);
						/*這一段是我加上去取得歷史訊息與聊天訊息的*/
						var jsonObj = {
								"type" : "history",
								"sender" : self,
								"receiver" :"1001",
								"message" : ""
							};
						webSocket.send(JSON.stringify(jsonObj));
						
					} else if ("history" === jsonObj.type) {
						messagesArea.innerHTML = '';
						var ul = document.createElement('ul');
						ul.id = "area";
						messagesArea.appendChild(ul);
						// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
						var messages = JSON.parse(jsonObj.message);
						for (var i = 0; i < messages.length; i++) {
							var historyData = JSON.parse(messages[i]);
							var showMsg = historyData.message;
							var li = document.createElement('li');
							// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
							historyData.sender === self ? li.className += 'me' : li.className += 'friend';
							li.innerHTML = showMsg;
							
							
							ul.appendChild(li);
						}
						messagesArea.scrollTop = messagesArea.scrollHeight;
					} else if ("chat" === jsonObj.type) {
						var li = document.createElement('li');
						jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
						li.innerHTML = jsonObj.message;
						console.log(li);
						document.getElementById("area").appendChild(li);
						messagesArea.scrollTop = messagesArea.scrollHeight;
					} else if ("close" === jsonObj.type) {
						refreshFriendList(jsonObj);
					}
					
				};
				
			

				webSocket.onclose = function(event) {
					console.log("Disconnected!");
				};
			}
			
			function sendMessage() {
				var inputMessage = document.getElementById("message");
				var friend = statusOutput.textContent;
				var message = inputMessage.value.trim();
	
				
				
				if (message === "") {
					alert("Input a message");
					inputMessage.focus();
				} else {
					var jsonObj = {
						"type" : "chat",
						"sender" : self,
						"receiver" :"1001",
						"message" : message
					};
					webSocket.send(JSON.stringify(jsonObj));
					inputMessage.value = "";
					inputMessage.focus();
				}
			}
			
			// 有好友上線或離線就更新列表
			function refreshFriendList(jsonObj) {
				var friends = jsonObj.users;
				var row = document.getElementById("row");
				row.innerHTML = '';
				for (var i = 0; i < friends.length; i++) {
					if (friends[i] === self) { continue; }                                                     /*註解好友列表*/
					row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' style="display:none;"><h2 >' + friends[i] + '</h2></div>';
				}
				addListener();
			}
			// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
			function addListener() {
				var container = document.getElementById("row");
				container.addEventListener("click", function(e) {
					//以下讓多餘的編號畫面隱藏
					var test = document.getElementById("statusOutput");   
					test.style.display="none";
					
					var friend = e.srcElement.textContent;
					updateFriendName("1001");
					var jsonObj = {
							"type" : "history",
							"sender" : self,
							"receiver" :"1001",
							"message" : ""
						};
					webSocket.send(JSON.stringify(jsonObj));
				});
			}
			
			function disconnect() {
				webSocket.close();
				document.getElementById('sendMessage').disabled = true;
				document.getElementById('connect').disabled = false;
				document.getElementById('disconnect').disabled = true;
			}
			
			function updateFriendName(name) {
				statusOutput.innerHTML = name;
			}
	</script>