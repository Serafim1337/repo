grid.addEventListener("click", function (e) {
  if (!e.target == "TH") return;
  if (e.target.dataset.order == "desc") {
    sortGrid(e.target.cellIndex, e.target.dataset.type, "desc");
    e.target.dataset.order = "asc";
  } else {
    sortGrid(e.target.cellIndex, e.target.dataset.type, "asc");
    e.target.dataset.order = "desc";
  }
});

function sortGrid(index, type, order) {
  let tbody = document.querySelector("tbody");

  let rows = Array.from(tbody.rows);

  switch (type) {
    case "number":
      if (order == "desc") {
        rows.sort(
          (rowA, rowB) =>
            rowA.cells[index].innerHTML - rowB.cells[index].innerHTML
        );
      } else {
        rows.sort(
          (rowA, rowB) =>
            rowB.cells[index].innerHTML - rowA.cells[index].innerHTML
        );
      }
    case "string":
      if (order == "desc") {
        rows.sort((rowA, rowB) =>
          rowA.cells[index].innerHTML > rowB.cells[index].innerHTML ? 1 : -1
        );
      } else {
        rows.sort((rowA, rowB) =>
          rowA.cells[index].innerHTML > rowB.cells[index].innerHTML ? -1 : 1
        );
      }
  }

  tbody.append(...rows);
}
