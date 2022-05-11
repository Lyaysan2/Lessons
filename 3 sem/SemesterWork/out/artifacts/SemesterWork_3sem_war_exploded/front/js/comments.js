$(document).ready(function (){
    let form = $("#comment-form")
    let commentsList = $("#comments-list")
    form.on('submit', function (){
        let text = form.find("#text").val();
        if (text === ''){
            return false
        }
        $.ajax("/addComment", {
            method: "POST",
            data: "text=" + text,
            headers: {
                'Content-Type':'application/x-www-form-urlencoded'
            },
            success: function (data){
                let comment = JSON.parse(data)

                form.find("#text").val("")

                let options = { year: "numeric", month: "numeric", day: "numeric", hour: "numeric", minute: "numeric" }

                let string;

                if(comment.user.avatarId !== 0){
                    string = "<img class=\"avatar\" alt=\"IMAGE\" src=\"/files/" + comment.user.avatarId + "\"/>\n"
                } else{
                    string = "<img class=\"avatar\" alt=\"IMAGE\" src=\"/img/no-avatar.jpg\"/>\n"
                }

                commentsList.prepend("<div class=\"comment-card\">\n" +
                    "                <div>\n" + string +
                    "                </div>\n" +
                    "\n" +
                    "\n" +
                    "                <div class=\"content\">\n" +
                    "                    <div class=\"name_date\">\n" +
                    "                        <div class=\"name\">" + comment.user.firstName + "</div>\n" +
                    "                        <div class=\"date\">" + new Date(comment.createdAt).toLocaleString('ru-RU', options) + "</div>\n" +
                    "                    </div>\n" +
                    "                    <div class=\"comment-text\">" + comment.text + "</div>\n" +
                    "                </div>\n" +
                    "            </div>")
            }
        })
        return false
    })
})
