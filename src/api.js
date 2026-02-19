// src/api.js
const BASE = process.env.REACT_APP_API_URL || "http://localhost:8080";

async function toJson(res) {
  const isJson = res.headers.get("content-type")?.includes("application/json");
  return isJson ? await res.json().catch(() => null) : null;
}

async function api(path, options) {
  const res = await fetch(BASE + path, {
    headers: { "Content-Type": "application/json" },
    ...options,
  });

  if (!res.ok) {
    const err = await toJson(res);
    throw new Error(err?.message || `Erro ${res.status}`);
  }
  if (res.status === 204) return null;
  return await toJson(res);
}

export const userApi = {
  list: () => api("/user"),
  create: (body) => api("/user", { method: "POST", body: JSON.stringify(body) }),
  update: (id, body) => api(`/user/${id}`, { method: "PUT", body: JSON.stringify(body) }),
  remove: (id) => api(`/user/${id}`, { method: "DELETE" }),
};

export const itemApi = {
  list: () => api("/item"),
  create: (body) => api("/item", { method: "POST", body: JSON.stringify(body) }),
  update: (id, body) => api(`/item/${id}`, { method: "PUT", body: JSON.stringify(body) }),
  addValue: (id, valor) => api(`/item/${id}/adicionar?valor=${valor}`, { method: "PATCH" }),
  remove: (id) => api(`/item/${id}`, { method: "DELETE" }),
};
