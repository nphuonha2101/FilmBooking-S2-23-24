
let handlesMessageSpan = {
    init: function () {
        let messageSpans = $$('message-span');
        for (let i = 0; i < messageSpans.length; i++) {
            messageSpans[i].addEventListener('change', function () {
                if (messageSpans[i].textContent === '' || messageSpans[i].textContent == null) {
                    messageSpans[i].style.display = 'none';
                } else {
                    messageSpans[i].style.display = 'block';
                }
            });
        }
    }
}

handlesMessageSpan.init();