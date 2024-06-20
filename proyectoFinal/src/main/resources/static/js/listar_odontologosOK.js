const tableBody = document.querySelector("#odontologosTable tbody");
function fetchOdontologos() {
    // listando los odontologos

    fetch(`/odontologo`)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            // Limpiar el contenido actual de la tabla
            tableBody.innerHTML = "";

            // Insertar los datos en la tabla
            data.forEach((odontologo, index) => {
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${odontologo.id}</td>
                    <td>${odontologo.nombre}</td>
                    <td>${odontologo.apellido}</td>
                    <td>${odontologo.numMatricula}</td>
                    <td>
                        <button class="btn btn-primary btn-sm" onclick="editOdontologo(${odontologo.id}, '${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.nroMatricula}')">Modificar</button>
                         <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
                    </td>
                 `;

                tableBody.appendChild(row);
            });
        })
        .catch((error) => {
            console.error("Error fetching data:", error);
        });
}

function editOdontologo(id, nombre, apellido, numMatricula) {
    const nuevoNombre = prompt("Ingrese el nuevo nombre:", nombre);
    const nuevoApellido = prompt("Ingrese el nuevo apellido:", apellido);
    const nuevoNroMatricula = prompt("Ingrese el nuevo número de matrícula:", numMatricula);

    if (nuevoNombre && nuevoApellido && nuevoNroMatricula) {
        const updatedOdontologo = {
            id: id,
            nombre: nuevoNombre,
            apellido: nuevoApellido,
            numMatricula: nuevoNroMatricula
        };

        fetch(`/odontologo`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedOdontologo)
        })
            .then(response => response.json())
            .then(data => {
                console.log("Odontólogo actualizado:", data);
                fetchOdontologos();
            })
            .catch(error => {
                console.error("Error actualizando odontologo:", error);
            });
    }
}

function deleteOdontologo(id) {
    if (confirm("¿Está seguro de que desea eliminar este odontólogo?")) {
        fetch(`/odontologo/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    console.log("Odontólogo eliminado");
                    fetchOdontologos();
                } else {
                    console.error("Error eliminando odontologo");
                }
            })
            .catch(error => {
                console.error("Error eliminando odontologo:", error);
            });
    }
}

fetchOdontologos();
