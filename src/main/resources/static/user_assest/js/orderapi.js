function saveOrder(button) {
    var orderId = button.getAttribute('data-id'); // ✅ read the ID from data attribute
    console.log(orderId);
    var packedValue = document.getElementById("packed-" + orderId).value;
    var dispatchValue = document.getElementById("dispatch-" + orderId).value;
    var outForDeliveryValue = document.getElementById("outForDelivery-" + orderId).value;
    var deliveredValue = document.getElementById("delivered-" + orderId).value;

    var orderData = {
        id: orderId,
        packed: packedValue,
        dispatch: dispatchValue,
        outForDelivery: outForDeliveryValue,
        delivered: deliveredValue
    };

    fetch("/updateOrderStatus/", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(orderData)
    })
    .then(response => {
        if (response.ok) {
            alert("Order updated successfully!");
        } else {
            alert("Failed to update order");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Error updating order");
    });
}
