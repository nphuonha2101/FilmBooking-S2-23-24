$('.modal').on('click', '.close-button', function () {
   const targetModal = $(this).closest('.modal');
   $(targetModal).hide();
});
