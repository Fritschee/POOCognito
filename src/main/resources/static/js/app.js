document.getElementById("reservaForm")
  .addEventListener("submit", async event => {
    event.preventDefault();

    const data = {
      modelo:      document.getElementById("modelo").value,
      dataInicio:  document.getElementById("inicio").value,
      dataFim:     document.getElementById("fim").value,
      precoPorDia: parseFloat(
                    document.getElementById("precoPorDia").value
                   )
    };

    try {
      const res = await fetch("/api/reservas", {
        method:  "POST",
        headers: { "Content-Type": "application/json" },
        body:    JSON.stringify(data)
      });

      if (res.ok) {
        alert("Reserva realizada com sucesso!");
      } else {
        const err = await res.text();
        alert(`Erro ao enviar reserva: ${err || res.statusText}`);
      }
    } catch (e) {
      console.error(e);
      alert("Erro de conexão ao enviar reserva.");
    }
  });
