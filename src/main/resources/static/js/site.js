//private method names from 'private'
//public method names as always

var Module = (function () {
    var myModule = {};

    myModule.publicMain = function () {
        console.log("JS publicMain() Entry Point");
        privateEntryPoint();
    };

    var privateEntryPoint = function () {
        $(document).ready(function () {
            privateBindHrefs();
            privateForms();
            privatePaginationHrefs();
        });
    };

    var privatePaginationHrefs = function () {
        $('#div-personlist').find('a').each(function (e) {
            var hrf = $(this).attr('href');
            $(this).attr('href', 'javascript:void(0);');
            $(this).click(function () {
                $('#inside-container').load(hrf + ' #inside-container > *', function () {
                    privatePaginationHrefs();
                });
                //$('#inside-container').load(hrf, function() {
                //    $(this).children(':first').unwrap();
                //    privatePaginationHrefs();
                //});
            });
        });
    };

    var privateForms = function () {
        //################################################################
        jQuery.validator.addMethod("accept", function (value, element, param) {
            return value.match(new RegExp("^" + param + "$"));
        });
        //################################################################
        $('#personFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: '/db/person/show',
                type: 'post',
                data: $('#personFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
                    console.log("ajax success.");
                    var res = $(response).find('#inside-container')[0];
                    $('#find-result').html(res);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('error(s):' + textStatus, errorThrown);
                }
            });
        });
        //################################################################
        $('#savePersonForm').validate({
            rules: {
                name: {
                    minlength: 1,
                    required: true,
                    accept: "[a-zA-Z]+"
                    //accept: "[0-9]+" <- only digits
                },
                birthday: {
                    required: true,
                    date: true
                },
                passport: {
                    minlength: 8,
                    maxlength: 8,
                    required: true,
                    accept: "[a-zA-Z0-9]+"
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.addClass('valid')
                    .closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            submitHandler: function (form) {
                $.ajax({
                    url: '/db/person/save',
                    type: 'post',
                    data: $('#savePersonForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        console.log("ajax success");
                        if (response == true) {
                            $('#person-result').html('<span class="text-success">' +
                            '<strong>New person was saved successfully</strong></span>');
                        }
                        if (response == false) {
                            $('#person-result').html('<span class="text-danger">' +
                            '<strong>Error saving new person. Check your data.</strong></span>');
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });
        //################################################################
        //$('#savePersonForm').submit(function (event) {
        //    event.preventDefault();
        //    $.ajax({
        //        url: '/db/person/save',
        //        type: 'post',
        //        data: $('#savePersonForm').serialize(),
        //        success: function (response, textStatus, jqXHR) {
        //            $('#result').html(response.toString());
        //            console.log("ajax success");
        //            if (response == true) {
        //                console.log("It's true!");
        //            }
        //            if (response == false) {
        //                console.log("It's false!");
        //            }
        //        },
        //        error: function (jqXHR, textStatus, errorThrown) {
        //            console.log('error(s):' + textStatus, errorThrown);
        //        }
        //    });
        //});
        //################################################################
        $('#findPersonForm').validate({
            rules: {
                name: {
                    required: false,
                    minlength: 1
                },
                birthday: {
                    required: false,
                    date: true
                },
                passport: {
                    required: false,
                    minlength: 8,
                    maxlength: 8
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.addClass('valid')
                    .closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            submitHandler: function (form) {
                $.ajax({
                    url: '/db/person/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findPersonForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        console.log("ajax success");
                        var res = $(response).find('#inside-container')[0];
                        $('#find-2-result').html(res);
                        privatePaginationHrefs();
                        //$.each(response, function(i, item) {
                        //    $('#find-2-result').append('{' + response[i].name + ', '
                        //    + response[i].birthday + ', ' + response[i].passport + '}, ');
                        //});
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });
        //################################################################

    };

    var privateBindHrefs = function () {
        //--------------------------------------------------------------
        //Left as an example
        //$('#hrefLogin').attr('onclick', 'Module.publicBindLogin();');

        //myModule.publicBindLogin = function () {
        //    $('#div-body').load('/login #div-login');
        //    return false;
        //};
        //--------------------------------------------------------------

        $('#hrefAdmin').click(function () {
            $("#div-db").load('/admin');
        });

        $('#printAllPersons-href').click(function () {
            $("#person-result").load('/db/person/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#get-into-db-href').click(function () {
            $("#div-body").load('/db #div-db');
        });

        $('#findPersonById-href').click(function () {
            $("#person-result").load('/db/person/find #inside-container', function () {
                privateForms();
            });
        });

        $('#savePerson-href').click(function () {
            $("#person-result").load('/db/person/save #inside-container', function () {
                privateForms();
            });
        });

        $('#findPersonByAny-href').click(function () {
            $("#person-result").load('/db/person/find #inside-container-2', function () {
                privateForms();
            });
        });

        $('#deletePerson-href').click(function () {
            $("#person-result").load('/db/person/delete #inside-container-2', function () {
                privateForms();
            });
        });
    };

    myModule.publicFunction = function () {
        //action
        return false;
    };

    return myModule; // returns the Object with public methods
})();

Module.publicMain();