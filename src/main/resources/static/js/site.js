//private method names from 'private'
//public method names as always

var Module = (function () {
    var myModule = {};

    myModule.publicMain = function () {
        //console.log("JS publicMain() Entry Point");
        privateEntryPoint();
    };

    var privateEntryPoint = function () {
        $(document).ready(function () {
            privateLinkBinding();
            privateForms();
            privatePaginationHrefs();
            privateMenuButtonColors();
            privateAjaxSetup();
        });
    };

    var privateAjaxSetup = function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajaxSetup({
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token)
            }
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
        //############################################################################################
        jQuery.validator.addMethod("accept", function (value, element, param) {
            return value.match(new RegExp("^" + param + "$"));
        });

        //############################################################################################
        //Forms FIND BY ID block
        //############################################################################################
        $('#personFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: 'person/show',
                type: 'post',
                data: $('#personFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
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
                url: 'teacher/show',
                type: 'post',
                beforeSend: function(xhr) {xhr.setRequestHeader(header, token)},
                data: $('#teacherFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
                    var res = $(response).find('#inside-container')[0];
                    $('#find-result').html(res);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('error(s):' + textStatus, errorThrown);
                }
            });
        });

        $('#formFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: 'form/show',
                type: 'post',
                data: $('#formFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
                    var res = $(response).find('#inside-container')[0];
                    $('#find-result').html(res);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('error(s):' + textStatus, errorThrown);
                }
            });
        });

        $('#groupFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: 'group/show',
                type: 'post',
                data: $('#groupFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
                    var res = $(response).find('#inside-container')[0];
                    $('#find-result').html(res);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('error(s):' + textStatus, errorThrown);
                }
            });
        });

        $('#markFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: 'mark/show',
                type: 'post',
                data: $('#markFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
                    var res = $(response).find('#inside-container')[0];
                    $('#find-result').html(res);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('error(s):' + textStatus, errorThrown);
                }
            });
        });

        $('#markTypeFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: 'marktype/show',
                type: 'post',
                data: $('#markTypeFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
                    var res = $(response).find('#inside-container')[0];
                    $('#find-result').html(res);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('error(s):' + textStatus, errorThrown);
                }
            });
        });

        $('#scheduleFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: 'schedule/show',
                type: 'post',
                data: $('#scheduleFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
                    var res = $(response).find('#inside-container')[0];
                    $('#find-result').html(res);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('error(s):' + textStatus, errorThrown);
                }
            });
        });

        $('#specializationFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: 'specialization/show',
                type: 'post',
                data: $('#specializationFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
                    var res = $(response).find('#inside-container')[0];
                    $('#find-result').html(res);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('error(s):' + textStatus, errorThrown);
                }
            });
        });

        $('#studentFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: 'student/show',
                type: 'post',
                data: $('#studentFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
                    var res = $(response).find('#inside-container')[0];
                    $('#find-result').html(res);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('error(s):' + textStatus, errorThrown);
                }
            });
        });

        $('#subjectFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: 'subject/show',
                type: 'post',
                data: $('#subjectFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
                    var res = $(response).find('#inside-container')[0];
                    $('#find-result').html(res);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('error(s):' + textStatus, errorThrown);
                }
            });
        });

        $('#authenticationFindByIdForm').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: 'authentication/show',
                type: 'post',
                data: $('#authenticationFindByIdForm').serialize(),
                success: function (response, textStatus, jqXHR) {
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
                    url: 'person/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findPersonForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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
                    url: 'teacher/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findTeacherForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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

        $('#findFormForm').validate({
            rules: {
                name: {
                    required: false,
                    minlength: 1
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
                    url: 'form/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findFormForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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

        $('#findGroupForm').validate({
            rules: {
                groupName: {
                    required: false,
                    minlength: 1
                },
                formName: {
                    required: false,
                    minlength: 1
                },
                specializationName: {
                    required: false,
                    minlength: 1
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
                    url: 'group/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findGroupForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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

        $('#findMarkForm').validate({
            rules: {
                markName: {
                    required: false,
                    minlength: 1
                },
                formName: {
                    required: false,
                    minlength: 1
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
                    url: 'mark/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findMarkForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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

        $('#findMarkTypeForm').validate({
            rules: {
                name: {
                    required: false,
                    minlength: 1
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
                    url: 'marktype/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findMarkTypeForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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

        $('#findScheduleForm').validate({
            rules: {
                name: {
                    required: false,
                    minlength: 1
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
                    url: 'schedule/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findScheduleForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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

        $('#findSpecializationForm').validate({
            rules: {
                name: {
                    required: false,
                    minlength: 1
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
                    url: 'specialization/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findSpecializationForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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

        $('#findStudentForm').validate({
            rules: {
                personName: {
                    required: false,
                    minlength: 1
                },
                groupName: {
                    required: false,
                    minlength: 1
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
                    url: 'student/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findStudentForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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

        $('#findSubjectForm').validate({
            rules: {
                subjectName: {
                    required: false,
                    minlength: 1
                },
                specializationName: {
                    required: false,
                    minlength: 1
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
                    url: 'subject/find',
                    type: 'GET',
                    //dataType: 'json',
                    data: $('#findSubjectForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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


        //############################################################################################
        //Forms SAVE
        //############################################################################################
        $('#savePersonForm').validate({
            rules: {
                name: {
                    minlength: 1,
                    required: true,
                    accept: "[ a-zA-Z]+"
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
                    url: 'person/save',
                    type: 'post',
                    data: $('#savePersonForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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
                    url: 'teacher/save',
                    type: 'post',
                    data: $('#saveTeacherForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
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

        $('#saveFormForm').validate({
            rules: {
                name: {
                    minlength: 1,
                    required: true,
                    accept: "[ a-zA-Z]+"
                    //accept: "[0-9]+" <- only digits
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
                    url: 'form/save',
                    type: 'post',
                    data: $('#saveFormForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        if (response == true) {
                            $('#form-result').html('<span class="text-success">' +
                                '<strong>New form was saved successfully</strong></span>');
                        }
                        if (response == false) {
                            $('#form-result').html('<span class="text-danger">' +
                                '<strong>Error saving new form. Check your data.</strong></span>');
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });

        $('#saveStudentForm').validate({
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
                    url: 'student/save',
                    type: 'post',
                    data: $('#saveStudentForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        if (response == true) {
                            $('#student-result').html('<span class="text-success">' +
                                '<strong>New student was saved successfully</strong></span>');
                        }
                        if (response == false) {
                            $('#student-result').html('<span class="text-danger">' +
                                '<strong>Error saving new student. Check your data.</strong></span>');
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });

        $('#saveGroupForm').validate({
            rules: {
                name: {
                    minlength: 1,
                    required: true,
                    accept: "[ a-zA-Z]+"
                    //accept: "[0-9]+" <- only digits
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
                    url: 'group/save',
                    type: 'post',
                    data: $('#saveGroupForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        if (response == true) {
                            $('#group-result').html('<span class="text-success">' +
                                '<strong>New group was saved successfully</strong></span>');
                        }
                        if (response == false) {
                            $('#group-result').html('<span class="text-danger">' +
                                '<strong>Error saving new group. Check your data.</strong></span>');
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });

        $('#saveMarkTypeForm').validate({
            rules: {
                name: {
                    minlength: 1,
                    required: true,
                    accept: "[ a-zA-Z]+"
                    //accept: "[0-9]+" <- only digits
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
                    url: 'marktype/save',
                    type: 'post',
                    data: $('#saveMarkTypeForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        if (response == true) {
                            $('#marktype-result').html('<span class="text-success">' +
                                '<strong>New markType was saved successfully</strong></span>');
                        }
                        if (response == false) {
                            $('#marktype-result').html('<span class="text-danger">' +
                                '<strong>Error saving new markType. Check your data.</strong></span>');
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });

        $('#saveMarkForm').validate({
            rules: {
                date: {
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
                    url: 'mark/save',
                    type: 'post',
                    data: $('#saveMarkForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        if (response == true) {
                            $('#mark-result').html('<span class="text-success">' +
                                '<strong>New mark was saved successfully</strong></span>');
                        }
                        if (response == false) {
                            $('#mark-result').html('<span class="text-danger">' +
                                '<strong>Error saving new mark. Check your data.</strong></span>');
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });

        $('#saveScheduleForm').validate({
            rules: {
                lenta: {
                    minlength: 1,
                    required: true,
                    accept: "[0-9]+"
                    //accept: "[0-9]+" <- only digits
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
                    url: 'schedule/save',
                    type: 'post',
                    data: $('#saveScheduleForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        if (response == true) {
                            $('#schedule-result').html('<span class="text-success">' +
                                '<strong>New schedule was saved successfully</strong></span>');
                        }
                        if (response == false) {
                            $('#schedule-result').html('<span class="text-danger">' +
                                '<strong>Error saving new schedule. Check your data.</strong></span>');
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });

        $('#saveSpecializationForm').validate({
            rules: {
                name: {
                    minlength: 1,
                    required: true,
                    accept: "[ a-zA-Z]+"
                    //accept: "[0-9]+" <- only digits
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
                    url: 'specialization/save',
                    type: 'post',
                    data: $('#saveSpecializationForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        if (response == true) {
                            $('#specialization-result').html('<span class="text-success">' +
                                '<strong>New specialization was saved successfully</strong></span>');
                        }
                        if (response == false) {
                            $('#specialization-result').html('<span class="text-danger">' +
                                '<strong>Error saving new specialization. Check your data.</strong></span>');
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('error(s):' + textStatus, errorThrown);
                    }
                });
            }
        });

        $('#saveSubjectForm').validate({
            rules: {
                name: {
                    minlength: 1,
                    required: true,
                    accept: "[ a-zA-Z]+"
                    //accept: "[0-9]+" <- only digits
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
                    url: 'subject/save',
                    type: 'post',
                    data: $('#saveSubjectForm').serialize(),
                    success: function (response, textStatus, jqXHR) {
                        if (response == true) {
                            $('#subject-result').html('<span class="text-success">' +
                                '<strong>New subject was saved successfully</strong></span>');
                        }
                        if (response == false) {
                            $('#subject-result').html('<span class="text-danger">' +
                                '<strong>Error saving new subject. Check your data.</strong></span>');
                        }
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
        //    $('#div-body').load('login #div-login');
        //    return false;
        //};
        //--------------------------------------------------------------

        $('#get-into-db-href').click(function () {
            $("#div-body").load('db #div-db');
        });


        //############################################################################################
        //DB buttons
        //############################################################################################
        $('#person-btn').attr('href', 'person');
        $('#teacher-btn').attr('href', 'teacher');
        $('#student-btn').attr('href', 'student');
        $('#group-btn').attr('href', 'group');
        $('#form-btn').attr('href', 'form');
        $('#mark-btn').attr('href', 'mark');
        $('#marktype-btn').attr('href', 'marktype');
        $('#schedule-btn').attr('href', 'schedule');
        $('#specialization-btn').attr('href', 'specialization');
        $('#subject-btn').attr('href', 'subject');
        //$('#authentication-btn').attr('href', 'authentication');
        //____________________________________END OF THE SECTION______________________________________


        //############################################################################################
        //Buttons SHOW ALL
        //############################################################################################
        $('#print-all-persons-btn').click(function () {
            $("#person-result").load('person/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#print-all-teachers-btn').click(function () {
            $("#teacher-result").load('teacher/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#print-all-students-btn').click(function () {
            $("#student-result").load('student/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#print-all-groups-btn').click(function () {
            $("#group-result").load('group/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#print-all-forms-btn').click(function () {
            $("#form-result").load('form/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#print-all-marks-btn').click(function () {
            $("#mark-result").load('mark/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#print-all-marktypes-btn').click(function () {
            $("#marktype-result").load('marktype/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#print-all-schedules-btn').click(function () {
            $("#schedule-result").load('schedule/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#print-all-specializations-btn').click(function () {
            $("#specialization-result").load('specialization/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#print-all-subjects-btn').click(function () {
            $("#subject-result").load('subject/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#print-all-authentications-btn').click(function () {
            $("#authentication-result").load('authentication/show_all #inside-container', function () {
                privatePaginationHrefs();
            });
        });
        //____________________________________END OF THE SECTION______________________________________


        //############################################################################################
        //Buttons FIND BY ID
        //############################################################################################
        $('#find-person-by-id-btn').click(function () {
            $("#person-result").load('person/find #inside-container', function () {
                privateForms();
            });
        });

        $('#find-teacher-by-id-btn').click(function () {
            $("#teacher-result").load('teacher/find #inside-container', function () {
                privateForms();
            });
        });

        $('#find-student-by-id-btn').click(function () {
            $("#student-result").load('student/find #inside-container', function () {
                privateForms();
            });
        });

        $('#find-group-by-id-btn').click(function () {
            $("#group-result").load('group/find #inside-container', function () {
                privateForms();
            });
        });

        $('#find-form-by-id-btn').click(function () {
            $("#form-result").load('form/find #inside-container', function () {
                privateForms();
            });
        });

        $('#find-mark-by-id-btn').click(function () {
            $("#mark-result").load('mark/find #inside-container', function () {
                privateForms();
            });
        });

        $('#find-marktype-by-id-btn').click(function () {
            $("#marktype-result").load('marktype/find #inside-container', function () {
                privateForms();
            });
        });

        $('#find-schedule-by-id-btn').click(function () {
            $("#schedule-result").load('schedule/find #inside-container', function () {
                privateForms();
            });
        });

        $('#find-specialization-by-id-btn').click(function () {
            $("#specialization-result").load('specialization/find #inside-container', function () {
                privateForms();
            });
        });

        $('#find-subject-by-id-btn').click(function () {
            $("#subject-result").load('subject/find #inside-container', function () {
                privateForms();
            });
        });

        $('#find-authentication-by-id-btn').click(function () {
            $("#authentication-result").load('authentication/find #inside-container', function () {
                privateForms();
            });
        });
        //____________________________________END OF THE SECTION______________________________________


        //############################################################################################
        //Buttons FIND BY ANY
        //############################################################################################
        $('#find-persons-by-any-btn').click(function () {
            $("#person-result").load('person/find #inside-container-2', function () {
                privateForms();
            });
        });

        $('#find-teachers-by-any-btn').click(function () {
            $("#teacher-result").load('teacher/find #inside-container-2', function () {
                privateForms();
            });
        });

        $('#find-students-by-any-btn').click(function () {
            $("#student-result").load('student/find #inside-container-2', function () {
                privateForms();
            });
        });

        $('#find-groups-by-any-btn').click(function () {
            $("#group-result").load('group/find #inside-container-2', function () {
                privateForms();
            });
        });

        $('#find-forms-by-any-btn').click(function () {
            $("#form-result").load('form/find #inside-container-2', function () {
                privateForms();
            });
        });

        $('#find-marks-by-any-btn').click(function () {
            $("#mark-result").load('mark/find #inside-container-2', function () {
                privateForms();
            });
        });

        $('#find-marktypes-by-any-btn').click(function () {
            $("#marktype-result").load('marktype/find #inside-container-2', function () {
                privateForms();
            });
        });

        $('#find-schedules-by-any-btn').click(function () {
            $("#schedule-result").load('schedule/find #inside-container-2', function () {
                privateForms();
            });
        });

        $('#find-specializations-by-any-btn').click(function () {
            $("#specialization-result").load('specialization/find #inside-container-2', function () {
                privateForms();
            });
        });

        $('#find-subjects-by-any-btn').click(function () {
            $("#subject-result").load('subject/find #inside-container-2', function () {
                privateForms();
            });
        });

        $('#find-authentications-by-any-btn').click(function () {
            $("#authentication-result").load('authentication/find #inside-container-2', function () {
                privateForms();
            });
        });
        //____________________________________END OF THE SECTION______________________________________


        //############################################################################################
        //Buttons SAVE
        //############################################################################################
        $('#save-person-btn').click(function () {
            $("#person-result").load('person/save?id= #inside-container', function () {
                privateForms();
            });
        });

        $('#save-teacher-btn').click(function () {
            $("#teacher-result").load('teacher/save?id= #inside-container', function () {
                privateForms();
            });
        });

        $('#save-student-btn').click(function () {
            $("#student-result").load('student/save?id= #inside-container', function () {
                privateForms();
            });
        });

        $('#save-group-btn').click(function () {
            $("#group-result").load('group/save?id= #inside-container', function () {
                privateForms();
            });
        });

        $('#save-form-btn').click(function () {
            $("#form-result").load('form/save?id= #inside-container', function () {
                privateForms();
            });
        });

        $('#save-mark-btn').click(function () {
            $("#mark-result").load('mark/save?id= #inside-container', function () {
                privateForms();
            });
        });

        $('#save-marktype-btn').click(function () {
            $("#marktype-result").load('marktype/save?id= #inside-container', function () {
                privateForms();
            });
        });

        $('#save-schedule-btn').click(function () {
            $("#schedule-result").load('schedule/save?id= #inside-container', function () {
                privateForms();
            });
        });

        $('#save-specialization-btn').click(function () {
            $("#specialization-result").load('specialization/save?id= #inside-container', function () {
                privateForms();
            });
        });

        $('#save-subject-btn').click(function () {
            $("#subject-result").load('subject/save?id= #inside-container', function () {
                privateForms();
            });
        });

        $('#save-authentication-btn').click(function () {
            $("#authentication-result").load('authentication/save?id= #inside-container', function () {
                privateForms();
            });
        });
        //____________________________________END OF THE SECTION______________________________________


        //############################################################################################
        //Buttons UPDATE
        //############################################################################################
        $('#update-person-btn').click(function () {
            $("#person-result").load('person/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#update-teacher-btn').click(function () {
            $("#teacher-result").load('teacher/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#update-student-btn').click(function () {
            $("#student-result").load('student/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#update-group-btn').click(function () {
            $("#group-result").load('group/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#update-form-btn').click(function () {
            $("#form-result").load('form/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#update-mark-btn').click(function () {
            $("#mark-result").load('mark/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#update-marktype-btn').click(function () {
            $("#marktype-result").load('marktype/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#update-schedule-btn').click(function () {
            $("#schedule-result").load('schedule/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#update-specialization-btn').click(function () {
            $("#specialization-result").load('specialization/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#update-subject-btn').click(function () {
            $("#subject-result").load('subject/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#update-authentication-btn').click(function () {
            $("#authentication-result").load('authentication/update #inside-container', function () {
                privatePaginationHrefs();
            });
        });
        //____________________________________END OF THE SECTION______________________________________


        //############################################################################################
        //Buttons DELETE
        //############################################################################################
        $('#delete-person-btn').click(function () {
            $("#person-result").load('person/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#delete-teacher-btn').click(function () {
            $("#teacher-result").load('teacher/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#delete-student-btn').click(function () {
            $("#student-result").load('student/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#delete-group-btn').click(function () {
            $("#group-result").load('group/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#delete-form-btn').click(function () {
            $("#form-result").load('form/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#delete-mark-btn').click(function () {
            $("#mark-result").load('mark/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#delete-marktype-btn').click(function () {
            $("#marktype-result").load('marktype/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#delete-schedule-btn').click(function () {
            $("#schedule-result").load('schedule/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#delete-specialization-btn').click(function () {
            $("#specialization-result").load('specialization/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#delete-subject-btn').click(function () {
            $("#subject-result").load('subject/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });

        $('#delete-authentication-btn').click(function () {
            $("#authentication-result").load('authentication/delete #inside-container', function () {
                privatePaginationHrefs();
            });
        });
        //____________________________________END OF THE SECTION______________________________________
    };

    var privateMenuButtonColors = function () {
        if ($("#div-edit-form").length > 0) {
            $("#form-btn").removeClass("btn-default").addClass("btn-warning");
        }
        if ($("#div-edit-group").length > 0) {
            $("#group-btn").removeClass("btn-default").addClass("btn-warning");
        }
        if ($("#div-edit-mark").length > 0) {
            $("#mark-btn").removeClass("btn-default").addClass("btn-warning");
        }
        if ($("#div-edit-marktype").length > 0) {
            $("#marktype-btn").removeClass("btn-default").addClass("btn-warning");
        }
        if ($("#div-edit-person").length > 0) {
            $("#person-btn").removeClass("btn-default").addClass("btn-warning");
        }
        if ($("#div-edit-schedule").length > 0) {
            $("#schedule-btn").removeClass("btn-default").addClass("btn-warning");
        }
        if ($("#div-edit-specialization").length > 0) {
            $("#specialization-btn").removeClass("btn-default").addClass("btn-warning");
        }
        if ($("#div-edit-student").length > 0) {
            $("#student-btn").removeClass("btn-default").addClass("btn-warning");
        }
        if ($("#div-edit-subject").length > 0) {
            $("#subject-btn").removeClass("btn-default").addClass("btn-warning");
        }
        if ($("#div-edit-teacher").length > 0) {
            $("#teacher-btn").removeClass("btn-default").addClass("btn-warning");
        }
        if ($("#div-edit-authentication").length > 0) {
            $("#authentication-btn").removeClass("btn-default").addClass("btn-warning");
        }
    };

    myModule.publicFunction = function () {
        //action
        return false;
    };

    return myModule; // returns the Object with public methods
})();

Module.publicMain();