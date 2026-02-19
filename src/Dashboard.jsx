import React, { useEffect, useState } from "react";
import { itemApi, userApi } from "./api";
import { Modal, Toast, money } from "./components";

export default function Dashboard() {
  const [users, setUsers] = useState([]);
  const [items, setItems] = useState([]);
  const [userId, setUserId] = useState("");

  const [toast, setToast] = useState(null);

  // Usuário
  const [userOpen, setUserOpen] = useState(false);
  const [userEdit, setUserEdit] = useState(null);
  const [uNome, setUNome] = useState("");
  const [uEmail, setUEmail] = useState("");
  const [uSenha, setUSenha] = useState("");

  // Item
  const [itemOpen, setItemOpen] = useState(false);
  const [itemEdit, setItemEdit] = useState(null);
  const [iNome, setINome] = useState("");
  const [iMeta, setIMeta] = useState("");

  // Adicionar valor
  const [addOpen, setAddOpen] = useState(false);
  const [addItem, setAddItem] = useState(null);
  const [addValue, setAddValue] = useState("");

  const showError = (msg) => setToast({ type: "error", title: "Erro", msg });

  const showSuccess = (msg) =>
    setToast({ type: "success", title: "Sucesso", msg });

  const showInfo = (msg) => setToast({ type: "info", title: "Atenção", msg });

  async function loadData() {
    try {
      const u = await userApi.list();
      const i = await itemApi.list();
      setUsers(u || []);
      setItems(i || []);
      if (!userId && u?.length) setUserId(String(u[0].id));
    } catch (e) {
      showError(e.message);
    }
  }

  useEffect(() => {
    loadData();

  }, []);

  const user = users.find((u) => String(u.id) === String(userId));
  const myItems = user
    ? items.filter((i) => String(i.idUser) === String(userId))
    : [];

  let prontos = 0;
  let faltando = 0;

  myItems.forEach((i) => {
    if (i.compraViavel) prontos++;
    const meta = Number(i.valorItem || 0);
    const caixa = Number(i.valorDisponivel || 0);
    if (caixa < meta) faltando += meta - caixa;
  });

  /* ================= USUÁRIO ================= */

  function openNewUser() {
    setUserEdit(null);
    setUNome("");
    setUEmail("");
    setUSenha("");
    setUserOpen(true);
  }

  function openEditUser() {
    if (!user) return;
    setUserEdit(user);
    setUNome(user.nome);
    setUEmail(user.email);
    setUSenha("");
    setUserOpen(true);
  }

  async function saveUser() {
    if (!uNome || !uEmail || !uSenha) {
      return showInfo("Preencha todos os campos.");
    }

    try {
      if (userEdit) {
        await userApi.update(userEdit.id, {
          nome: uNome,
          email: uEmail,
          senha: uSenha,
        });
      } else {
        const created = await userApi.create({
          nome: uNome,
          email: uEmail,
          senha: uSenha,
        });
        setUserId(String(created.id));
      }
      setUserOpen(false);
      loadData();
    } catch (e) {
      showError(e.message);
    }
  }

  async function deleteUser() {
    if (!user) return;
    if (!window.confirm(`Excluir o usuário "${user.nome}"?`)) return;

    try {
      await userApi.remove(user.id);

      // escolhe o próximo usuário manualmente
      const remaining = users.filter(
        (u) => String(u.id) !== String(user.id),
      );

      if (remaining.length > 0) {
        setUserId(String(remaining[0].id));
      } else {
        setUserId("");
      }

      loadData();
    } catch (e) {
      showError(e.message);
    }
  }

  /* ================= ITENS ================= */

  function openNewItem() {
    if (!user) {
      return showInfo(
        "Antes de criar um desejo, você precisa ter um usuário ativo",
      );
    }

    setItemEdit(null);
    setINome("");
    setIMeta("");
    setItemOpen(true);
  }

  function openEditItem(item) {
    setItemEdit(item);
    setINome(item.nomeItem);
    setIMeta(item.valorItem);
    setItemOpen(true);
  }

  async function saveItem() {
    const meta = Number(iMeta);
    if (!iNome || meta <= 0) {
      return showInfo("Informe um nome e uma meta válida.");
    }

    try {
      const body = {
        nomeItem: iNome,
        valorItem: Number(iMeta),
        valorDisponivel: itemEdit ? itemEdit.valorDisponivel : 0,
        idUsuario: Number(userId),
      };

      if (itemEdit) {
        await itemApi.update(itemEdit.id_item, body);
      } else {
        await itemApi.create(body);
      }

      setItemOpen(false);
      loadData();
    } catch (e) {
      showError(e.message);
    }
    console.log("ID do usuário selecionado no estado:", userId);
    console.log("Objeto completo enviado:");
  }

  async function deleteItem(item) {
    if (!window.confirm(`Excluir "${item.nomeItem}"?`)) return;
    await itemApi.remove(item.id_item);
    loadData();
  }

  /* ================= ADICIONAR VALOR ================= */

  function openAddValue(item) {
    setAddItem(item);
    setAddValue("");
    setAddOpen(true);
  }

  async function saveAddValue() {
    const v = Number(addValue);
    if (v <= 0) return showInfo("Digite um valor maior que zero.");

    try {
      await itemApi.addValue(addItem.id_item, v);
      setAddOpen(false);
      loadData();
    } catch (e) {
      showError(e.message);
    }
  }

  /* ================= UI ================= */

  return (
    <>
      <header className="header">
        <div>
          <h1>Cofre de Desejos</h1>
          <p className="sub">
            {user
              ? `Acompanhando os desejos de ${user.nome}`
              : "Crie um cofre e guarde dinheiro aos poucos para realizar seus desejos"}
          </p>
        </div>

        <button className="btn primary" onClick={openNewUser}>
          Novo usuário
        </button>
      </header>

      <div className="layout">
        <aside className="card sidebar">
          <label>Usuário ativo</label>

          <select
            className="selectUser"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            aria-label="Selecionar usuário"
          >
            {!users.length && <option key="none">Nenhum usuário</option>}
            {users.map((u) => (
              <option key={u.id} value={u.id}>
                {u.nome}
              </option>
            ))}
          </select>

          <div className="row">
            <button
              className="btn ghost small"
              onClick={openEditUser}
              disabled={!user}
            >
              Editar
            </button>
            <button
              className="btn danger small"
              onClick={deleteUser}
              disabled={!user}
            >
              Excluir
            </button>
          </div>

          <p className="hint">
            Cada usuário possui seu próprio cofre de desejos.
          </p>
        </aside>

        <main className="content">
          <section className="card summary">
            <div>
              <h2>Resumo geral</h2>
              <p className="sub">
                {user
                  ? `${prontos} desejos prontos • faltam ${money(faltando)}`
                  : "—"}
              </p>
            </div>

            <button className="btn primary" onClick={openNewItem}>
              Novo desejo
            </button>
          </section>

          {!user ? (
            <section className="card empty">
              <h3>Comece criando um usuário</h3>
              <p>Depois adicione desejos e acompanhe o progresso.</p>
            </section>
          ) : myItems.length === 0 ? (
            <section className="card empty">
              <h3>Nenhum desejo cadastrado</h3>
              <p>Adicione algo que você deseja comprar.</p>
            </section>
          ) : (
            <section className="grid">
              {myItems.map((i) => {
                console.log("Dados do item que chegou da API:", i);
                const pct = Math.min(
                  100,
                  Math.round((i.valorDisponivel / i.valorItem) * 100),
                );

                return (
                  <article key={i.id_item} className="card item">
                    <div className="itemTop">
                      <h3>{i.nomeItem}</h3>
                      <span className={`pill ${i.compraViavel ? "ok" : ""}`}>
                        {i.compraViavel ? "✨ Realizável" : `${pct}%`}
                      </span>
                    </div>

                    <p className="meta">
                      {money(i.valorDisponivel)} de {money(i.valorItem)}
                    </p>

                    <p className={`hintBadge ${i.compraViavel ? "ok" : ""}`}>
                      {i.compraViavel
                        ? "Você já pode realizar esse desejo ✨"
                        : `Faltam ${money(i.valorItem - i.valorDisponivel)} para a meta`}
                    </p>

                    <div className="bar">
                      <div style={{ width: pct + "%" }} />
                    </div>

                    <div className="row">
                      <button
                        className="btn primary small"
                        onClick={() => openAddValue(i)}
                      >
                        Guardar dinheiro
                      </button>

                      <button
                        className="btn ghost small"
                        onClick={() => openEditItem(i)}
                      >
                        Editar
                      </button>

                      <button
                        className="btn danger small"
                        onClick={() => deleteItem(i)}
                      >
                        Excluir
                      </button>
                    </div>
                  </article>
                );
              })}
            </section>
          )}
        </main>
      </div>

      {/* MODAIS */}

      <Modal open={userOpen} title="Usuário" onClose={() => setUserOpen(false)}>
        <div className="modalForm">
          <label>Nome</label>
          <input
            placeholder="Ex: João Silva"
            value={uNome}
            onChange={(e) => setUNome(e.target.value)}
          />

          <label>Email</label>
          <input
            placeholder="email@exemplo.com"
            value={uEmail}
            onChange={(e) => setUEmail(e.target.value)}
          />

          <label>Senha</label>
          <input
            type="password"
            placeholder="Defina uma senha"
            value={uSenha}
            onChange={(e) => setUSenha(e.target.value)}
          />
        </div>

        <div className="row">
          <button className="btn primary" onClick={saveUser}>
            Salvar
          </button>
        </div>
      </Modal>

      <Modal open={itemOpen} title="Desejo" onClose={() => setItemOpen(false)}>
        <div className="modalForm">
          <label>Nome do desejo</label>
          <input
            placeholder="Ex: Notebook, Viagem..."
            value={iNome}
            onChange={(e) => setINome(e.target.value)}
          />

          <label>Meta financeira (R$)</label>
          <input
            type="number"
            placeholder="Ex: 3500"
            value={iMeta}
            onChange={(e) => setIMeta(e.target.value)}
          />
        </div>

        <div className="row">
          <button className="btn primary" onClick={saveItem}>
            Salvar
          </button>
        </div>
      </Modal>

      <Modal
        open={addOpen}
        title="Guardar dinheiro"
        onClose={() => setAddOpen(false)}
      >
        <div className="modalForm">
          <label>Valor a adicionar (R$)</label>
          <input
            type="number"
            placeholder="Ex: 200"
            value={addValue}
            onChange={(e) => setAddValue(e.target.value)}
          />
        </div>

        <div className="row">
          <button className="btn primary" onClick={saveAddValue}>
            Adicionar
          </button>
        </div>
      </Modal>

      <Toast t={toast} close={() => setToast(null)} />
    </>
  );
}
