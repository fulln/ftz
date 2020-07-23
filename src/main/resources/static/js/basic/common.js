// keyup事件
function keyUpValue(obj) {
    return obj.value.replace(/[^\=\:\-\a-\z\A-\Z0-9\u4E00-\u9FA5\.\s]/g, '');
}

// 生成随机id
function createUniqueId(n) {
    let random = function () { // 生成10-12位不等的字符串
        return Number(Math.random().toString().substr(2)).toString(36); // 转换成十六进制
    }
    let arr = [];

    function createId() {
        let num = random();
        let _bool = false;
        arr.forEach(v => {
            if (v === num) _bool = true;
        });
        if (_bool) {
            createId();
        } else {
            arr.push(num);
        }
    }

    let i = 0;
    while (i < n) {
        createId();
        i++;
    }
    return arr;
}

// 提示框
function alertInfo(header, message, btns, methods, method2) {
    //示范一个公告层
    layer.open({
        type: 1
        , title: header
        , closeBtn: false
        , area: '300px;'
        , shade: 0.8
        , id: 'LAY_LAYUIPRO' //设定一个id，防止重复弹出
        , btn: btns
        , btn2: function (index, layer0) {
            if (NotEmpty(method2)) {
                method2(index, layer0);
            }
        }
        , yes: function (index, layero) {
            if (NotEmpty(methods)) {
                methods(index, layero);
            }
            layer.close(index);
        }
        , moveType: 1 //拖拽模式，0或者1
        , content: message
    });
}

function alertNoTitle(message, methods) {
    alertInfo("提示", message, ['ok'], methods)
}

function alertAuto(message) {
    layer.msg(message);
}

/******** 时间 *******/
//时间差
function datecompare(d1, d2) {
    var date1 = new Date(d1).format("yyyy-mm-dd");
    var date2 = new Date(d2).format("yyyy-mm-dd");
    var DIF = date1 - date2;
    return DIF;
}

//时间戳格式转时间格式
function getLocalTime(nS, format) {
    return new Date(parseInt(nS)).format(format);
}

//
function tableDateFormat(value) {
    return NotEmpty(value) ? getLocalTime(value, "yyyy-MM-dd HH:mm:ss") : new Date();
}

//时间格式化("括号内是你要输入的格式")日期转string
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "H+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }

    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

function GetDateTimeformat(time, format) {
    var t = new Date(time);
    var tf = function (i) {
        return (i < 10 ? '0' : '') + i
    };
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
        switch (a) {
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    });
}

//ajax
function requestBack(type, url, params, callBack) {
    $.ajax({
        cache: false,
        type: type,
        url: url,
        data: params,
        beforeSend: function (request) {
            request.setRequestHeader("name", "fulln");
            request.setRequestHeader("token", "112233");
        },
        dataType: 'json',
        success: function (data) {
            if (NotEmpty(data)) {
                if (data.code > 0) {
                    callBack(data);
                } else {
                    alertNoTitle(data.message);
                }
            } else {
                alertNoTitle("未找到数据")
            }
        },
        error: function (request) {
            console.log("查询失败！")
        }
    });
}

/**
 * @return {boolean} 非空判断
 */
function NotEmpty(value) {
    if (value == null || value == "" || typeof (value) == "undefined" || value == 0) {
        return false
    } else {
        return true
    }
}

/**
 * @return {boolean}
 */
function IsEmpty(value) {
    return !NotEmpty(value)
}

function null20(value) {
    if (value == null || value == "" || value == "0") {
        return 0;
    } else {
        return value
    }
}

let regexCommon = function () {
    function notnull(data) {
        if (!NotEmpty(data)) {
            return "不能为空";
        }
        return true;
    }

    let textInput = function (value) {
        let contans = notnull(value);
        if (contans !== true) {
            return contans;
        }

        if (/(^\_)|(\__)|(\_+$)/.test(value)) {
            return '首尾不能出现下划线\'_\'';
        }
        if (/^\d+\d+\d$/.test(value)) {
            return '不能全为数字';
        }
        return true;
    };

    function textReq(value) {
        let contans = textInput(value);
        if (contans !== true) {
            return contans;
        }
        if (!new RegExp("^[a-zA-Z0-9,.\-_\u4e00-\u9fa5\\s·]+$").test(value)) {
            return '不能有特殊字符';
        }
    }

    function textLength(value, a, b) {
        if (a > b) {
            return "长度限制错误";
        }
        let contans = textInput(value);
        if (contans !== true) {
            return contans;
        }
        if (!new RegExp("^[a-zA-Z0-9,\._\u4e00-\u9fa5\\s·]{" + a + "," + b + "}$").test(value)) {
            return '不在限制的长度范围内';
        }
    }

    function textMD(value) {
        let contans = textInput(value);
        if (contans !== true) {
            return contans;
        }
        if (!new RegExp("^[a-zA-Z0-9,.\-_\u4e00-\u9fa5\\s·]+$").test(value)) {
            return '不能有特殊字符';
        }
        if (!new RegExp(".md$").test(value)) {
            return '请以.md结尾';
        }
    }

    return {
        notnull: function (value) {
            return notnull(value);
        },
        textReq: function (value, item) {
            return textReq(value);
        },
        textLength: function (value, a, b, item) {
            return textLength(value, a, b);
        },
        textMD: function (value, item) {
            return textMD(value)
        }
    }
}();

/************** layui *********/
let layCommon = function () {

    let laydate, table, layform, tab, singleparams;

    layui.use(['laydate', 'table', 'form'], function () {
        if (!NotEmpty(laydate) && !NotEmpty(layform) && !NotEmpty(table)) {
            table = layui.table //日期
                , laydate = layui.laydate
                , layform = layui.form;
        }
    });

    /**
     * 表格初始化
     * @param obj
     * @param urlParams
     * @param headColums
     * @returns {*}
     */
    let initTable = function (obj, urlParams, headColums) {
        singleparams = urlParams.queryParams;
        let handle = {};
        tab = {
            elem: obj
            , url: urlParams.url //数据接口
            , headers: urlParams.headers ? urlParams.headers : {}
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , method: urlParams.method ? urlParams.method : 'GET'
            , request: {
                pageName: "pageNo"
                , limitName: "pageSize"
            }
            , where: urlParams.queryParams()
            , parseData: function (res) { //res 即为原始返回的数据//
                let handle;
                if (res.code < 0) {
                    alertNoTitle(res.message);
                    handle = {
                        count: 0,
                        data: null
                    };
                } else {
                    handle = urlParams.responseHandler(res);
                    $.extend(handle,{
                            "count": res.data.total, //解析数据长度
                            "data": res.data.records,  //解析数据列表
                            "curr": res.data.current,
                            "size": res.data.size,
                        });
                    res.code = 0;
                }

                if (handle.count === 0) {
                    res.message = "未找到数据"
                }
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": handle.count ? handle.count : 0, //解析数据长度
                    "data": handle.data//解析数据列表
                };
            }
            , page: {
                count: handle.count ? handle.count : 0, //解析数据长度
                limits: [10, 50, 100],
                limit: handle.size,
                curr: handle.curr,
                groups: 3,
                hash: 'pageNo'
            } //开启分页
            , loading: true
            , cols: [headColums]
            , defaultToolbar: ['filter', 'print', 'exports']
            , text: {
                none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
            }
        };
        table.set(tab);
        return table.render(tab);
    };

    /**
     * 日期控件
     * @param obj 开始
     * @param obj2 结束
     * @param day 间隔天数
     * @param laydate
     */
    let initDate = function (obj, obj2) {

        let start = {
            elem: obj, //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            // event: 'focus', //响应事件。如果没有传入event，则按照默认的click
            type: 'datetime',
            istime: true,
            max: 0,
            min: -30,
            done: function (value, date) {
                end.min = date; //开始日选好后，重置结束日的最小日期
                end.max = date.date + 7; //将结束日的初始值设定为开始日
            }
        };

        let end = {
            elem: obj2, //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            // event: 'focus', //响应事件。如果没有传入event，则按照默认的click
            type: 'datetime',
            istime: true,
            max: 0,
            done: function (value, date) {
                start.max = date;
                start.min = date.date - 7;
            }
        };

        laydate.render(start);
        laydate.render(end);

    };


    let getParams = function (formId) {
        let $ = layui.jquery;
        let _params = {};
        $.each($('#' + formId).serializeArray(), function (i, field) {
            if (null != field.value && "" != field.value) {
                _params[field.name] = field.value;
            }
        });
        return _params;
    };

    let upload = function (eleId, layUpload, done, error, accept, exts) {
        layUpload.render({
            elem: eleId //绑定元素
            , url: '/upload/' //上传接口
            , accept: accept === undefined ? 'file' : accept
            , exts: exts === undefined ? 'jpg|png|gif|bmp|jpeg' : exts
            , done: function (res) {
                //上传完毕回调
                if (typeof (done) === 'function') {
                    done(res)
                }
            }
            , error: function () {
                //请求异常回调
                if (typeof (error) === 'function') {
                    error()
                }
            }
        });
    };

    let ListenTools = function (obj, method) {
        table.on('tool(' + obj + ')', function (obj) {
            method(obj);
        });
    };

    let openFrame = function (url, title, width, height) {
        width = width === undefined ? '900px' : width;
        height = height === undefined ? '500px' : height;
        return top.layer.open({
            area: [width, height],
            type: 2,
            title: title,
            content: url //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        });
    };

    function layformVerify(obj, params, methods) {
        if (NotEmpty(params)) {
            layform.verify(params);
        }
        layform.on('submit(' + obj + ')', function (data) {
            console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
            console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
            console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            methods(data);
            return false;
        })
    }

    function fromInit(obj, params) {

        layform.val(obj, params);

    }

    function reloadTable(obj) {
        tab.where = singleparams();
        table.reload(obj, tab);
    }

    return {
        initTable: function (obj, urlParams, headColums) {
            return initTable(obj, urlParams, headColums);
        },
        reloadTable: function (obj) {
            reloadTable(obj);
        },
        uploadFile: function (eleId, layUpload, done, error, accept, exts) {
            upload(eleId, layUpload, done, error, accept, exts);
        },
        openFrame: function (url, title, width, height) {
            return openFrame(url, title, width, height);
        },
        initDate: function (obj, obj2) {
            return initDate(obj, obj2)
        },
        varidator: function (obj, params, method) {

            return layformVerify(obj, params, method)
        },
        formInit: function (obj, param) {
            fromInit(obj, param)
        },
        ListenTools: function (obj, method) {
            ListenTools(obj, method)
        }
    }
}();


