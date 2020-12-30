$(document).ready(function () {

  $('#jqGrid').jqGrid({
    datatype: 'local',
    // data: dataArr,
    height: 250,
    width: 500,
    colNames: ['User ID', 'Password', 'Email', 'Tel'],

    colModel: [
      {
        name: 'userId',
        index: 'userId',
        width: 15,
        align: 'center',
        hidden: false,
      },

      {
        name: 'password',
        index: 'password',
        width: 70,
        align: 'left',
        hidden: false,
      },

      {
        name: 'email',
        index: 'email',
        width: 70,
        align: 'center',
        hidden: false,
      },

      {
        name: 'tel',
        index: 'tel',
        width: 70,
        resizable: true,
        align: 'right',
        hidden: false,
      },
    ],

    loadtext: '로딩중일때 표시되는 텍스트!',
    //caption: 'jQuery Grid: jqGrid 샘플',

    pager: '#gridpager',
    rowNum: 9,

    //rownumbers:true,
    //viewrecords:true,
    //pgbuttons:true,
    //pginput:true,
    //shrinkToFit:true,
    //sortable: false,
    //loadComplete:function(data){},
    //scroll:true,
    //loadonce:false,
    //hidegrid:true
  });
    // change size of jqGrid by parent size.
  $('#jqGrid').jqGrid('setGridWidth', $('#container').width());
  loadData();
});



$(function () {
  // var dataArr = [
  //   {
  //     userId: 'cjaesung',
  //     password: 'cjaesung',
  //     email: 'cjaesung@gmail.com',
  //     tel: '01054778937',
  //   },
  //   {
  //     userId: 'cjaesung1',
  //     password: 'cjaesung1',
  //     email: 'cjaesung1@gmail.com',
  //     tel: '01012341521',
  //   },
  // ];

  $('#btn_submit').click(function (e) {
    e.preventDefault();

    loadData();
  });
});

function loadData() {
    var form = $("#register_form")[0];
    var formData = new FormData(form);

    $.ajax({
            cache : false,
            url : "/register",
            processData: false,
            contentType: false,
            type : 'POST',
            data : formData,
            success : function(data) {
                var gParams = $('#jqGrid').jqGrid('getGridParam');
                gParams.data = data;
                $('#jqGrid').trigger('reloadGrid', [{ page: 1 }]);
            }, // success

            error : function(xhr, status) {
                alert(xhr + " : " + status);
            }
        }); // $.ajax */    }
}

function clearForm() {
  $('#user_id').val('');
  $('#password').val('');
  $('#email').val('');
  $('#tel').val('');

  $('#user_id').focus();
}
