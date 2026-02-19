// src/components.jsx
import React, { useEffect } from "react";

export const money = (v) =>
  Number(v || 0).toLocaleString("pt-BR", { style: "currency", currency: "BRL" });

export function Toast({ t, close }) {
  useEffect(() => {
    if (!t) return;
    const timer = setTimeout(close, 3200);
    return () => clearTimeout(timer);
  }, [t, close]);

  if (!t) return null;

  return (
    <div className={`toast ${t.type || "info"}`}>
      <strong>{t.title}</strong>
      <p>{t.msg}</p>
    </div>
  );
}


export function Modal({ open, title, subtitle, onClose, children }) {
  if (!open) return null;

  return (
    <div className="modalOverlay" onMouseDown={onClose}>
      <div className="modal" onMouseDown={(e) => e.stopPropagation()}>
        <div className="modalHeader">
          <div>
            <h3>{title}</h3>
            {subtitle ? <p>{subtitle}</p> : null}
          </div>
          <button className="btn ghost small" onClick={onClose}>
            Fechar
          </button>
        </div>

        <div className="hr" />

        {}
        <div className="modalBody">
          {children}
        </div>
      </div>
    </div>
  );
}
