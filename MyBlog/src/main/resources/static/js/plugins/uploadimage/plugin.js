/**
 * Created by PC on 2017/7/10.
 */
/**
 * tinymce plugin
 * Created by jerry on 16/8/5.
 */
tinymce.PluginManager.add('uploadimage', function (editor) {

    function selectLocalImages() {
        var dom = editor.dom;
        var input_f = $('<input type="file" name="thumbnail" accept="image/jpg,image/jpeg,image/png,image/gif" multiple="multiple">');
        input_f.on('change', function () {
            var form = $("<form/>",
                {
                    action: editor.settings.upload_image_url, //设置上传图片的路由，配置在初始化时
                    style: 'display:none',
                    method: 'post',
                    enctype: 'multipart/form-data'
                }
            );
            form.append(input_f);
            form.ajaxSubmit({
                beforeSubmit: function () {
                    return true;
                },
                success: function (data) {
                    if (data && data.file_path) {
                        editor.focus();
                        data.file_path.forEach(function (src) {
                            editor.selection.setContent(dom.createHTML('img', {src: src}));
                        })
                    }
                }
            });
        });

        input_f.click();
    }

    editor.addCommand("mceUploadImageEditor", selectLocalImages);

    editor.addButton('uploadimage', {
        icon: 'image',
        tooltip: 'Insert img',
        onclick: selectLocalImages
    });

    editor.addMenuItem('uploadimage', {
        icon: 'image',
        text: 'Insert img',
        context: 'tools',
        onclick: selectLocalImages
    });
});