import React from 'react';
import './WithModal.css';
const withModal = (WrappedComponent: any) => {
  class Hoc extends React.Component {
    render() {
      const { ...props } = this.props;
      return (
        <div className="modal-background">
          <div className="modal-panel">
            <WrappedComponent {...props} />
          </div>
        </div>
      );
    }
  }

  return Hoc;
};

export default withModal;
