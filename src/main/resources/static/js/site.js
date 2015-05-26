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
            privateLinkBinding();
            privateForms();
            privatePaginationHrefs();
        });
    };

    var privatePaginationHrefs = function () {
        $('#pagination').find('a').each(function (e) {
            var hrf = $(this).attr('href');
            $(this).attr('href', 'javascript:void(0);');
            $(this).click(function () {
                $('#inside-container').load(hrf + ' #inside-container > *', function () {
                    privatePaginationHrefs();
                });
            });
        });

        $('#delete-body').find('a').each(function (e) {
            var hrf = $(this).attr('href');
            $(this).attr('href', 'javascript:void(0);');
            $(this).click(function () {
                $('#inside-container').load(hrf + ' #inside-container > *', function () {
                    privatePaginationHrefs();
                });
            });
        });

        $('#update-body').find('a').each(function (e) {
            var hrf = $(this).attr('href');
            $(this).attr('href', 'javascript:void(0);');
            $(this).click(function () {
                $('#inside-container').load(hrf + ' #inside-container > *', function () {
                    privatePaginationHrefs();
                    privateForms();
                });
            });
        });


    };

    var privateForms = function () {
        //################################################################
        jQuery.validator.addMethod("accept", function (value, element, param) {
            return value.match(new RegExp("^" + param + "$"));
        });


        //############################################################################################
        //Forms FIND BY ID block
        //############################################################################################
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

        $('#teacherFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: '/db/teacher/show',
                type: 'post',
                data: $('#teacherFindByIdForm').serialize(),
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
        //____________________________________END OF THE SECTION______________________________________





        //############################################################################################
        //Forms SAVE
        //############################################################################################
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

        $('#saveTeacherForm').validate({
            rules: {
                start: {
                    required: true,
                    date: true
                },
                finish: {
                    required: true,
                    date: true
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
                    url: '/db/teacher/save',
                    type: 'post',
                    data: $('#saveTeacherForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        console.log("ajax success");
                        if (response == true) {
                            $('#teacher-result').html('<span class="text-success">' +
                                '<strong>New teacher was saved successfully</strong></span>');
                        }
                        if (response == false) {
                            $('#teacher-result').html('<span class="text-danger">' +
                                '<strong>Error saving new teacher. Check your data.</strong></span>');
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });
        //____________________________________END OF THE SECTION______________________________________






        //############################################################################################
        //Forms FIND BY ANY
        //############################################################################################
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
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });

        $('#findTeacherForm').validate({
            rules: {
                name: {
                    required: false,
                    minlength: 1
                },
                start: {
                    required: false,
                    date: true
                },
                finish: {
                    required: false,
                    date: true
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
                    url: '/db/teacher/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findTeacherForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        console.log("ajax success");
                        var res = $(response).find('#inside-container')[0];
                        $('#find-2-result').html(res);
                        privatePaginationHrefs();
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });
        //____________________________________END OF THE SECTION______________________________________

    };

    var privateLinkBinding = function () {
        //--------------------------------------------------------------
        //Left as an example
        //$('#hrefLogin').attr('onclick', 'Module.publicBindLogin();');

        //myModule.publicBindLogin = function () {
        //    $('#div-body').load('/login #div-login');
        //    return false;
        //};
        //--------------------------------------------------------------

        $('#get-into-db-href').click(function () {
            $("#div-body").load('/db #div-db');
        });


        //############################################################################################
        //DB buttons
        //############################################################################################
        $('#person-btn').attr('href', '/db/person');
        $('#teacher-btn').attr('href', '/db/teacher');
        $('#student-btn').attr('href', '/db/student');
        $('#group-btn').attr('href', '/db/group');
        $('#form-btn').attr('href', '/db/form');
        $('#mark-btn').attr('href', '/db/mark');
        $('#mark-type-btn').attr('href', '/db/marktype');
        $('#schedule-btn').attr('href', '/db/schedule');
        $('#spec-btn').attr('href', '/db/specialization');
        $('#subject-btn').attr('href', '/db/subject');
        $('#authentication-btn').attr('href', '/db/authentication');
        //____________________________________END OF THE SECTION______________________________________

        

        //############################################################################################
        //Buttons SHOW ALL
        //############################################################################################
        $('#print-all-persons-btn').click(function () {
            $("#person-result").load('/db/person/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#print-all-teachers-btn').click(function () {
            $("#teacher-result").load('/db/teacher/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });
        //____________________________________END OF THE SECTION______________________________________




        //############################################################################################
        //Buttons FIND BY ID
        //############################################################################################
        $('#find-person-by-id-btn').click(function () {
            $("#person-result").load('/db/person/find #inside-container', function () {
                privateForms();
            });
        });
        $('#find-teacher-by-id-btn').click(function () {
            $("#teacher-result").load('/db/teacher/find #inside-container', function () {
                privateForms();
            });
        });
        //____________________________________END OF THE SECTION______________________________________




        //############################################################################################
        //Buttons FIND BY ANY
        //############################################################################################
        $('#find-persons-by-any-btn').click(function () {
            $("#person-result").load('/db/person/find #inside-container-2', function () {
                privateForms();
            });
        });
        $('#find-teachers-by-any-btn').click(function () {
            $("#teacher-result").load('/db/teacher/find #inside-container-2', function () {
                privateForms();
            });
        });
        //____________________________________END OF THE SECTION______________________________________




        //############################################################################################
        //Buttons SAVE
        //############################################################################################
        $('#save-person-btn').click(function () {
            $("#person-result").load('/db/person/save?id= #inside-container', function () {
                privateForms();
            });
        });

        $('#save-teacher-btn').click(function () {
            $("#teacher-result").load('/db/teacher/save?id= #inside-container', function () {
                privateForms();
            });
        });
        //____________________________________END OF THE SECTION______________________________________



        //############################################################################################
        //Buttons UPDATE
        //############################################################################################
        $('#update-person-btn').click(function () {
            $("#person-result").load('/db/person/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });
        $('#update-teacher-btn').click(function () {
            $("#teacher-result").load('/db/teacher/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });
        //____________________________________END OF THE SECTION______________________________________

        
        
        
        
        //############################################################################################
        //Buttons DELETE
        //############################################################################################
        $('#delete-person-btn').click(function () {
            $("#person-result").load('/db/person/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });
        $('#delete-teacher-btn').click(function () {
            $("#teacher-result").load('/db/teacher/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });
        //____________________________________END OF THE SECTION______________________________________
    };

    myModule.publicFunction = function () {
        //action
        return false;
    };

    return myModule; // returns the Object with public methods
})();

Module.publicMain();