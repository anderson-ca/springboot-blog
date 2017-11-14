(function($) {
    var request = $.ajax({'url': '/posts.json'});
    request.done( function (data) {
        var html = '';
        data.forEach( function (post) {
            html += '<div>';
            html += '<h1>' + post.title + '</h1>';
            html += '<p>' + post.body + '</p>';
            html += '<p>Posted by ' + post.user.name + '</p>';
            html += '</div>';
        });
        $('#posts').html(html);
    })
})(jQuery);