function addProductItem(){
    console.log("Function Called");
}

function addItem() {
  console.log("function called");
  const formData = new URLSearchParams();
  formData.append('name', document.getElementById('name').value);
  console.log(document.getElementById('name').value);
  formData.append('sku', document.getElementById('sku').value);
  formData.append('categoryName', document.getElementById('categoryName').value);
  formData.append('size', document.getElementById('size').value);
  formData.append('price', document.getElementById('price').value);
  formData.append('discount', document.getElementById('discount').value);
  formData.append('discountPrice', document.getElementById('discountPrice').value);

  console.log('Sending:', formData.toString()); // Debug line

  fetch('/MiniProject2/addProduct', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: formData.toString(),
  })
  .then(response => response.text())
  .then(data => {
    console.log('Server response:', data);
    alert(data);
  })
  .catch(error => {
    console.error('Error:', error);
    alert('Error adding product.');
  });
}