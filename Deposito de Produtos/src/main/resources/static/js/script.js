
document.querySelector('.setorSub').style.display = 'none';
document.querySelector('.produtoSub').style.display = 'none';

let lista = [
    document.querySelector('#addS'),
    document.querySelector('#editS'),
    document.querySelector('#removeS'),
    document.querySelector('#listS')
];

for (const elemento of lista) {
    elemento.style.backgroundColor = 'transparent';
}
function toggleMenuAside(subClass) {
    const sub = document.querySelector(subClass);
    if (sub.style.display === 'none' || sub.style.display === '') {
        sub.style.display = 'block';
    } else {
        sub.style.display = 'none';
    }
}

function displayForms(item, form){
    const element = document.querySelector(form);
    const element2 = document.querySelector(item);
    if (element.style.display === 'none' || element.style.display === '') {
        element.style.display = 'block';
        element2.style.backgroundColor = 'rgba(111, 66, 193, 0.5)'
    } else {
        element.style.display = 'none';
        element2.style.backgroundColor = 'transparent'
    }
}

document.querySelector('.reloadButton').addEventListener('click', function(){
    location.reload();
});

document.addEventListener('DOMContentLoaded', function () {
    function handleFormSubmit(formId, url) {
        var setorForm = document.getElementById(formId);
        var messageBox = document.getElementById('messageBox');

        setorForm.addEventListener('submit', function (event) {
            event.preventDefault();
            var formData = new FormData(setorForm);

            fetch(url, {
                method: 'POST',
                body: formData,
            })
                .then(function (response) {
                    return response.json();
                })
                .then(function (data) {
                    if (data.error) {
                        // Exibe um alerta de erro
                        alert(data.error);
                    } else {
                        // Exibe a mensagem na página
                        messageBox.innerHTML = '<div class="alert alert-success">' + data.message + '</div>';
                    }
                })
                .catch(function (error) {
                    alert('Ocorreu um erro na requisição fetch.');
                });
        });
    }

    // Chame a função para cada formulário que você deseja que funcione
    handleFormSubmit('addSetorForm', '/setor/addSetor');
    handleFormSubmit('editSetorForm', '/setor/updateSetor');
    handleFormSubmit('removeSetorForm', '/setor/deleteSetor');
});



