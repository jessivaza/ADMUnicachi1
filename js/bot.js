function openChatWindow() {
    document.getElementById('chat-window').style.display = 'block';
}

function closeChatWindow() {
    document.getElementById('chat-window').style.display = 'none';
}

function sendMessage() {
    const inputField = document.getElementById('chat-input-field');
    const message = inputField.value.trim();

    if (message === "") {
        return; // No enviar mensajes vacíos
    }

    // Agregar mensaje del usuario
    addMessageToChat(message, 'user-message');

    // Limpiar el campo de entrada
    inputField.value = '';

    // Simular respuesta del bot
    setTimeout(() => {
        if (message.toLowerCase().includes('asesor')) {
            const response1 = "Hola, ¿cómo puedo ayudarte?";
            const response2 = "¿Qué quieres comprar? Puedes contactar a nuestro asesor por WhatsApp haciendo clic(https://wa.me/902525307).";
            addMessageToChat(response1, 'bot-message');
            addMessageToChat(response2, 'bot-message');
        } else {
            addMessageToChat("Hola, ¿cómo puedo ayudarte?", 'bot-message');
            addMessageToChat("¿Qué quieres comprar?", 'bot-message');
        }
    }, 800);
}

function addMessageToChat(message, className) {
    const chatHistory = document.getElementById('chat-history');
    const messageElement = document.createElement('div');
    messageElement.className = `chat-message ${className}`;
    messageElement.textContent = message;
    chatHistory.appendChild(messageElement);

    // Desplazar hacia abajo para ver el último mensaje
    chatHistory.scrollTop = chatHistory.scrollHeight;
}
document.getElementById("whatsapp-button").addEventListener("click", function () {
    window.location.href = "https://wa.me/902525307"; // Reemplaza "1234567890" con el número de teléfono de WhatsApp
});
function mostrarPoliticas() {
    const politicas = "Políticas de Devolución:\n\n" +
                      "1. Tienes un plazo de 3 días para devolver el producto.\n" +
                      "2. El producto debe ser devuelto en buen estado.\n" +
                      "3. Se otorga un plazo de 5 días para la devolución del dinero.";

    // Agregar mensaje de las políticas al chat con clase y estilos adecuados
    addMessageToChat(politicas, 'user-message-politicas');

    // Simular respuesta del bot
    setTimeout(() => {
        // Aquí puedes agregar la lógica de respuesta del bot si es necesaria
    }, 800);
}

