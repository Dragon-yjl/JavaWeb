function load() {
    alert("load OK")

}
$(function () {//jquery�ķ�ʽ��body������֮��ִ�еĴ���
    alert("load OK")
    //���淢��һ����������
    //jquery��ajax�������첽�ķ�ʽ����http��������
    //�����Ĵ����������json��ʽ����

    let data = {
        username: "abc",
        password: "123"
    };

    $.ajax({//����������Լ�ֵ����ʽ����
        type: "POST",//���󷽷�
        url: "some.php",//����·��
        contentType: "application/json",//������������ͣ�(Ĭ��:"application/x-www/form-urlencoded")
        data: JSON.stringify(data),//��������,�������������json����Ҫ��json����ת��Ϊ�ַ���
        success: function (msg) {
            alert("Data Saved:" + msg)

        },error: function (XMLHttpRequest, textStatus, errorThrown) {
            //ͨ��textStatus �� errorThrown ֮��
            //ֻ��һ���������Ϣ
            this; //���ñ���AJAX����ʱ���ݵ�option����

        }

    });
});