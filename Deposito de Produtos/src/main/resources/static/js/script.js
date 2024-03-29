let quantidade = 0;
document.querySelector('.setorSub').style.display = 'none';
document.querySelector('.produtoSub').style.display = 'none';
document.querySelector('.funcionarioSub').style.display = 'none';
document.querySelector('.depositoSub').style.display = 'none';

let lista = [
    document.querySelector('#addS'),
    document.querySelector('#addSP'),
    document.querySelector('#addP'),
    document.querySelector('#addF'),
    document.querySelector('#addFS'),
    document.querySelector('#addD'),
    document.querySelector('#addDS'),
    document.querySelector('#editS'),
    document.querySelector('#editP'),
    document.querySelector('#editF'),
    document.querySelector('#editD'),
    document.querySelector('#removeS'),
    document.querySelector('#removeSP'),
    document.querySelector('#removeP'),
    document.querySelector('#removeF'),
    document.querySelector('#removeFS'),
    document.querySelector('#removeD'),
    document.querySelector('#removeDS'),
    document.querySelector('#listS'),
    document.querySelector('#listP'),
    document.querySelector('#listF'),
    document.querySelector('#listD'),
    document.querySelector('#listE')
];

for (const elemento of lista) {
    elemento.style.backgroundColor = 'transparent';
}

function hideAllAside(){
    const formElements = document.querySelectorAll('.sub');
    formElements.forEach((element) => {
        element.style.display = 'none';
    });
}
function hideAllForms() {
    const formElements = document.querySelectorAll('.form-signin');
    formElements.forEach((element) => {
        element.style.display = 'none';
    });
    // Reset o background-color de todos os itens da lista
    const listItems = document.querySelectorAll('.sub-item');
    listItems.forEach((item) => {
        item.style.backgroundColor = 'transparent';
    });
}
function toggleMenuAside(subClass) {
    const sub = document.querySelector(subClass);
    if(sub.style.display === 'none'){
        hideAllAside();
        hideAllForms();
        quantidade = 0;
    }
    if (sub.style.display === 'none' || sub.style.display === '') {
        sub.style.display = 'block';
    } else {
        sub.style.display = 'none';

    }
}


function displayForms(item, form){
    const element = document.querySelector(form);
    const element2 = document.querySelector(item);
    if(element.style.display === 'none' && quantidade >= 2){
        hideAllForms();
        quantidade = 0;
    }
    if (element.style.display === 'none' || element.style.display === '') {
        element.style.display = 'flex';
        element.style.flexDirection = 'column';
        element.style.alignItems = 'center';
        element.style.flexBasis = 'content'
        element2.style.backgroundColor = 'rgba(111, 66, 193, 0.5)'
        quantidade++;
    } else {
        element.style.display = 'none';
        element2.style.backgroundColor = 'transparent'
        quantidade--;
    }
}

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
                        console.log(data.error)
                        alert(data.error);
                    } else {
                        // Exibe a mensagem na página
                        messageBox.innerHTML = '<div class="alert alert-success">' + data.message + '</div>';
                    }
                })
                .catch(function (error) {
                    alert('Ocorreu um erro na requisição.');
                    console.log(error.message)
                });
        });
    }

    // Chame a função para cada formulário que você deseja que funcione
    // Setor
    handleFormSubmit('addSetorForm', '/setor/addSetor');
    handleFormSubmit('editSetorForm', '/setor/updateSetor');
    handleFormSubmit('removeSetorForm', '/setor/deleteSetor');
    handleFormSubmit('addProdutoSetorForm', '/setor/addProduto');
    handleFormSubmit('removeProdutoSetorForm', '/setor/addProduto');
    // Produto
    handleFormSubmit('addProdutoForm', '/produto/addProduto');
    handleFormSubmit('editProdutoForm', '/produto/updateProduto');
    handleFormSubmit('removeProdutoForm', '/produto/deleteProduto');
    // Funcionário
    handleFormSubmit('addFuncionarioForm', '/funcionario/addFuncionario');
    handleFormSubmit('editFuncionarioForm', '/funcionario/updateFuncionario');
    handleFormSubmit('removeFuncionarioForm', '/funcionario/deleteFuncionario');
    handleFormSubmit('addSetorFuncionarioForm', '/funcionario/addSetor');
    handleFormSubmit('removeSetorFuncioanarioForm', '/funcionario/deleteSetor');
    // Deposito
    handleFormSubmit('addDepositoForm', '/deposito/addDeposito');
    handleFormSubmit('editDepositoForm', '/deposito/updateDeposito');
    handleFormSubmit('removeDepositoForm', '/deposito/deleteDeposito');
    handleFormSubmit('addSetorDepositoForm', '/deposito/addSetor');
    handleFormSubmit('removeSetorDepositoForm', '/deposito/deleteSetor');

});

// document.querySelector(".reloadButton").addEventListener("click", function() {
function listItens(endpoint, tabelaId, mensagem){
    const url = `http://localhost:8080${endpoint}`;
    const tabela = document.getElementById(tabelaId);
    const thead = tabela.querySelector("thead");
    const tbody = tabela.querySelector("tbody");
    const msg = document.querySelector(`#${mensagem}`);

    // Limpar a tabela existente
    thead.innerHTML = "";
    tbody.innerHTML = "";

    fetch(url)
        .then(response => response.json())
        .then(data => {
            // Verificar se há dados para exibir
            if (data.length === 0) {
                msg.style.display = 'block';
                // alert("Sem dados para exibir.")
                return;
            }

            // Criar cabeçalho da tabela
            const headerRow = document.createElement("tr");
            for (const key in data[0]) {
                const th = document.createElement("th");
                th.textContent = key;
                headerRow.appendChild(th);
            }
            thead.appendChild(headerRow);

            // Iterar pelos dados e criar linhas da tabela
            data.forEach(item => {
                const row = document.createElement("tr");

                for (const key in item) {
                    if (item.hasOwnProperty(key)) {
                        const cell = document.createElement("td");
                        const value = item[key];

                        if (typeof value === 'object' && value !== null) {
                            // Se for um objeto não nulo, exibir como uma string JSON
                            const jsonArray = Array.isArray(value) ? value : [value]; // Verifique se é um array ou objeto único
                            cell.textContent = jsonArray.map(obj => ' ' + obj.id);
                        } else {
                            // Se for null ou outro tipo, definir 'null' na célula
                            cell.textContent = value === null ? 'null' : value;
                        }

                        row.appendChild(cell);
                    }
                }

                tbody.appendChild(row);
            });
            if(endpoint !== '/funcionario/getEnderecos'){
                const urlSplitted = url.split("/");
                const element = '.list' + urlSplitted[3].charAt(0).toUpperCase() + urlSplitted[3].slice(1);
                document.querySelector(element).style.maxWidth = tabela.clientWidth + 70 + 'px';
                document.querySelector(element).style.maxHeight = '550px';
            }else{
                document.querySelector('.listEnderecos').style.maxWidth = tabela.clientWidth + 70 + 'px';
                document.querySelector('.listEnderecos').style.maxHeight = '550px';
                document.querySelector('.listEnderecos').style.overflow = 'scroll';
            }
            msg.style.display = 'none';

        })
        .catch(error => {
            console.error("Erro ao carregar os dados:", error);
        });
}


